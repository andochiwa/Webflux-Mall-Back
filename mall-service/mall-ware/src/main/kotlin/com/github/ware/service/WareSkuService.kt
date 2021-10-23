package com.github.ware.service

import com.github.ware.dao.WareSkuDao
import com.github.ware.entity.WareSku
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
* @date 2021-09-26 03:53:23
*/
@Service
class WareSkuService {
    @Autowired
    lateinit var wareSkuDao: WareSkuDao

    @Autowired
    lateinit var template: R2dbcEntityTemplate

    suspend fun getById(id: Long): WareSku? {
        return wareSkuDao.findById(id)
    }

    suspend fun saveOrUpdate(wareSku: WareSku): WareSku {
        return wareSkuDao.save(wareSku)
    }

    suspend fun deleteByIds(ids: List<Long>) {
        wareSkuDao.deleteAllById(ids)
    }

    fun getAll(): Flow<WareSku> {
        return wareSkuDao.findAll()
    }

    suspend fun getPagination(page: Int, limit: Int, skuId: Long?, wareId: Long?): Map<String, Any> {
        var criteria = Criteria.empty()
        val pageRequest = PageRequest.of(page, limit)
        if (skuId?.equals(0L) == false) {
            criteria = criteria.and("sku_id").isEqual(skuId)
        }
        if (wareId?.equals(0L) == false) {
            criteria = criteria.and("ware_id").isEqual(wareId)
        }
        val list = template.select<WareSku>()
            .matching(Query.query(criteria).with(pageRequest))
            .flow().toList()
        val count = template.count(Query.query(criteria), WareSku::class.java).awaitSingle()
        return mutableMapOf<String, Any>().apply {
            this["list"] = list
            this["totalCount"] = count
        }
    }
}

