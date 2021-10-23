package com.github.coupon.service

import com.github.coupon.dao.MemberPriceDao
import com.github.coupon.dao.SkuFullReductionDao
import com.github.coupon.dao.SkuLadderDao
import com.github.coupon.entity.MemberPrice
import com.github.coupon.entity.SkuFullReduction
import com.github.coupon.entity.SkuLadder
import com.github.to.SkuFullReductionTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 23:39:59
 */
@Service
class SkuFullReductionService {
    @Autowired
    lateinit var skuFullReductionDao: SkuFullReductionDao

    @Autowired
    lateinit var skuLadderDao: SkuLadderDao

    @Autowired
    lateinit var memberPriceDao: MemberPriceDao


    suspend fun getById(id: Long): SkuFullReduction? {
        return skuFullReductionDao.findById(id)
    }

    suspend fun saveOrUpdate(skuFullReduction: SkuFullReduction): SkuFullReduction {
        return skuFullReductionDao.save(skuFullReduction)
    }

    suspend fun deleteById(id: Long) {
        skuFullReductionDao.deleteById(id)
    }

    fun getAll(): Flow<SkuFullReduction> {
        return skuFullReductionDao.findAll()
    }

    @Transactional
    suspend fun saveSkuReduction(skuFullReductionTo: List<SkuFullReductionTo>) {
        // 保存优惠，满减, 会员价格等信息
        val skuLadderList = mutableListOf<SkuLadder>()
        var skuFullReductionList = mutableListOf<SkuFullReduction>()
        val memberPriceList = mutableListOf<MemberPrice>()
        skuFullReductionTo.forEach { to ->
            SkuLadder().apply {
                skuId = to.skuId
                fullCount = to.fullCount ?: return
                discount = to.discount
                price = to.price
                addOther = to.countStatus
            }.run { skuLadderList += this }

            SkuFullReduction().apply {
                skuId = to.skuId
                fullPrice = to.fullPrice ?: return@apply
                reducePrice = to.reducePrice ?: return@apply
                addOther = to.countStatus
            }.run { skuFullReductionList += this }

            to.memberPrice?.map {
                MemberPrice().apply {
                    skuId = to.skuId
                    memberLevelId = it.id
                    memberLevelName = it.name
                    memberPrice = it.price
                    addOther = 1
                }
            }?.run { memberPriceList += this }
        }
        // filter invalid data
        skuFullReductionList = skuFullReductionList.filter {
            it.fullPrice!! >= it.reducePrice!! && it.fullPrice!! > BigDecimal(0) && it.reducePrice!! > BigDecimal(0)
        } as MutableList<SkuFullReduction>
        skuLadderDao.saveAll(skuLadderList).toList()
        skuFullReductionDao.saveAll(skuFullReductionList).toList()
        memberPriceDao.saveAll(memberPriceList).toList()
    }
}

