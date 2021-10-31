package com.github.ware.service

import com.github.ware.dao.WareOrderTaskDao
import com.github.ware.entity.WareOrderTask
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
import org.springframework.stereotype.Service

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:23
 */
@Service
class WareOrderTaskService {
    @Autowired
    lateinit var wareOrderTaskDao: WareOrderTaskDao

    @Autowired
    lateinit var template: R2dbcEntityTemplate

    suspend fun getById(id: Long): WareOrderTask? {
        return wareOrderTaskDao.findById(id)
    }

    suspend fun saveOrUpdate(wareOrderTask: WareOrderTask): WareOrderTask {
        return wareOrderTaskDao.save(wareOrderTask)
    }

    suspend fun deleteByIds(ids: List<Long>) {
        wareOrderTaskDao.deleteAllById(ids)
    }

    fun getAll(): Flow<WareOrderTask> {
        return wareOrderTaskDao.findAll()
    }

    suspend fun getPagination(page: Int, limit: Int, key: String?): Map<String, Any> {
        var criteria = Criteria.empty()
        key?.run { criteria = criteria.and("consignee").like("%${key}%") }

        val pageRequest = PageRequest.of(page, limit)
        val list = template.select<WareOrderTask>()
            .matching(Query.query(criteria).with(pageRequest))
            .flow().toList()
        val count = template.count(Query.query(criteria), WareOrderTask::class.java)
            .awaitSingle()
        return mutableMapOf<String, Any>().apply {
            this["list"] = list
            this["totalCount"] = count
        }
    }
}

