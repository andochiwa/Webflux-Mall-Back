package com.github.ware.service

import com.github.ware.dao.PurchaseDetailDao
import com.github.ware.entity.PurchaseDetail
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

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-26 03:53:24
*/
@Service
class PurchaseDetailService {
    @Autowired
    lateinit var purchaseDetailDao: PurchaseDetailDao

    @Autowired
    lateinit var template: R2dbcEntityTemplate

    suspend fun getById(id: Long): PurchaseDetail? {
        return purchaseDetailDao.findById(id)
    }

    suspend fun saveOrUpdate(purchaseDetail: PurchaseDetail): PurchaseDetail {
        return purchaseDetailDao.save(purchaseDetail)
    }

    suspend fun deleteByIds(ids: List<Long>) {
        purchaseDetailDao.deleteAllById(ids)
    }

    fun getAll(): Flow<PurchaseDetail> {
        return purchaseDetailDao.findAll()
    }

    suspend fun getOnConditions(page: Int, limit: Int, wareId: Long?, status: Int?): Map<String, Any> {
        var criteria = Criteria.empty()
        if (wareId?.equals(0L) == false) {
            criteria = criteria.and("ware_id").isEqual(wareId)
        }
        status?.run { criteria = criteria.and("status").isEqual(this) }

        val pageRequest = PageRequest.of(page, limit)
        val list = template.select<PurchaseDetail>()
            .matching(Query.query(criteria).with(pageRequest))
            .flow().toList()
        val count = template.count(Query.query(criteria), PurchaseDetail::class.java).awaitSingle()
        return mutableMapOf<String, Any>().apply {
            this["list"] = list
            this["count"] = count
        }
    }
}

