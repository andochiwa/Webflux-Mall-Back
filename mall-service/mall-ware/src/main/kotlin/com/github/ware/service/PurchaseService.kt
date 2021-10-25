package com.github.ware.service

import cn.hutool.core.bean.BeanUtil
import com.github.constant.PurchaseDetailStatusEnum
import com.github.constant.PurchaseStatusEnum
import com.github.to.SkuInfoTo
import com.github.ware.dao.PurchaseDao
import com.github.ware.dao.PurchaseDetailDao
import com.github.ware.dao.WareSkuDao
import com.github.ware.entity.Purchase
import com.github.ware.entity.PurchaseDetail
import com.github.ware.entity.WareSku
import com.github.ware.feign.ProductFeign
import com.github.ware.vo.MergeVo
import com.github.ware.vo.PurchaseDoneVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.r2dbc.core.select
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.isEqual
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:23
 */
@Service
class PurchaseService {
    @Autowired
    lateinit var purchaseDao: PurchaseDao

    @Autowired
    lateinit var template: R2dbcEntityTemplate

    @Autowired
    lateinit var purchaseDetailDao: PurchaseDetailDao

    @Autowired
    lateinit var wareSkuDao: WareSkuDao

    @Autowired
    lateinit var productFeign: ProductFeign

    suspend fun getById(id: Long): Purchase? {
        return purchaseDao.findById(id)
    }

    suspend fun saveOrUpdate(purchase: Purchase): Purchase {
        purchase.createTime ?: run { purchase.createTime = LocalDateTime.now() }
        purchase.updateTime = LocalDateTime.now()
        return purchaseDao.save(purchase)
    }

    suspend fun deleteByIds(ids: List<Long>) {
        purchaseDao.deleteAllById(ids)
    }

    fun getAll(): Flow<Purchase> {
        return purchaseDao.findAll()
    }

    suspend fun getOnConditions(page: Int, limit: Int, status: Int?, key: String?): Map<String, Any> {
        val pageRequest = PageRequest.of(page, limit)
        var criteria = Criteria.empty()
        status?.run { criteria = criteria.and("status").isEqual(status) }
        if (key?.isNotEmpty() == true) {
            criteria = criteria.and("assignee_name").like("%${key}%")
        }
        val list = template.select<Purchase>()
            .matching(Query.query(criteria).with(pageRequest))
            .flow().toList()
        val count = template.count(Query.query(criteria), Purchase::class.java).awaitSingle()
        return mutableMapOf<String, Any>().apply {
            this["list"] = list
            this["totalCount"] = count
        }
    }

    suspend fun getUnreceivedList(): List<Purchase> {
        return purchaseDao.getByStatusIn(listOf(0, 1)).toList()
    }

    @Transactional
    suspend fun merge(mergeVo: MergeVo) {
        val purchaseId =
            mergeVo.purchaseId ?: this.saveOrUpdate(Purchase(status = PurchaseStatusEnum.CREATES.value)).id!!
        mergeVo.items.onEach {
            // 确认采购单是新增或未分配
            val purchaseDetail = purchaseDetailDao.findById(it)
            if (purchaseDetail?.status?.equals(PurchaseDetailStatusEnum.CREATES.value) == true
                || purchaseDetail?.status?.equals(PurchaseDetailStatusEnum.ASSIGNED.value) == true
            ) {
                purchaseDetailDao.updatePurchaseIdAndStatusById(it, purchaseId, PurchaseDetailStatusEnum.ASSIGNED.value)
            }
        }

    }

    @Transactional
    suspend fun receivedPurchase(purchaseIds: List<Long>) {
        val updateTime = LocalDateTime.now()
        purchaseDao.findAllById(purchaseIds)
            .filter { it.status == PurchaseStatusEnum.ASSIGNED.value } // 确认采购单是已分配状态
            .onEach {
                it.status = PurchaseStatusEnum.RECEIVE.value
                it.updateTime = updateTime
            }
            .run { purchaseDao.saveAll(this).toList() }
            .map { it.id!! } // 改变采购单状态
            .run { purchaseDetailDao.getAllByPurchaseIdIn(this) } // 改变采购项状态
            .onEach { it.status = PurchaseDetailStatusEnum.BUYING.value }
            .run { purchaseDetailDao.saveAll(this) }
            .toList()
    }

    @Transactional
    suspend fun completePurchase(purchaseDoneVo: PurchaseDoneVo) {
        val purchaseId = purchaseDoneVo.id
        var successFlag = true
        // 设置采购项状态
        purchaseDoneVo.items.map {
            PurchaseDetail().apply {
                id = it.itemId
                status = it.status
                // todo need reason field to failure
            }
        }.onEach {
            if (it.status == PurchaseDetailStatusEnum.BUY_FAIL.value) {
                successFlag = false
            }
            purchaseDetailDao.updateStatusById(it.id, it.status)
        }.filter {
            // 过滤采购失败的信息
            it.status == PurchaseDetailStatusEnum.FINISH.value
        }.map {
            it.id!!
        }.run {
            purchaseDetailDao.findAllById(this)
        }.onEach {
            // 入库, 如果没有库则创建一个库
            if (wareSkuDao.countBySkuIdAndWareId(it.skuId!!, it.wareId!!) == 0L) {
                val wareSku = WareSku().apply {
                    skuId = it.skuId
                    wareId = it.wareId
                    stock = it.skuNum
                    // 远程查询sku_name
                    val resultDto = productFeign.getSkuInfoById(skuId!!).awaitSingle()
                    if (resultDto.code == 200) {
                        val skuInfoTo = BeanUtil.toBean(resultDto.data["skuInfo"], SkuInfoTo::class.java)
                        // todo: skuInfo cannot be null
                        skuName = skuInfoTo?.skuName
                    }
                }
                wareSkuDao.save(wareSku)
            } else {
                wareSkuDao.updateStockBySkuIdAndWareId(it.skuId!!, it.wareId!!, it.skuNum)
            }
        }.toList()
        // 设置采购单状态
        this.getById(purchaseId!!)?.apply {
            status = if (successFlag) PurchaseStatusEnum.FINISH.value else PurchaseStatusEnum.HAS_ERROR.value
        }?.run {
            saveOrUpdate(this)
        } ?: throw NullPointerException("采购单为空")

    }
}

