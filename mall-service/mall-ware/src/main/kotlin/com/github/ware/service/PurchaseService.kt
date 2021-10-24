package com.github.ware.service

import com.github.constant.PurchaseDetailStatusEnum
import com.github.constant.PurchaseStatusEnum
import com.github.ware.dao.PurchaseDao
import com.github.ware.dao.PurchaseDetailDao
import com.github.ware.entity.Purchase
import com.github.ware.vo.MergeVo
import kotlinx.coroutines.flow.Flow
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
        println(mergeVo)

        val purchaseId =
            mergeVo.purchaseId ?: this.saveOrUpdate(Purchase(status = PurchaseStatusEnum.CREATES.value)).id!!
        mergeVo.items.onEach {
            purchaseDetailDao.updatePurchaseIdAndStatusById(it, purchaseId, PurchaseDetailStatusEnum.ASSIGNED.value)
        }

    }
}

