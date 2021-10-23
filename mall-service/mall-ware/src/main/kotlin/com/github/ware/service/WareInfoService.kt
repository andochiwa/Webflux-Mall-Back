package com.github.ware.service

import com.github.ware.dao.WareInfoDao
import com.github.ware.entity.WareInfo
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
 * @date 2021-09-26 03:53:24
 */
@Service
class WareInfoService {
    @Autowired
    lateinit var wareInfoDao: WareInfoDao

    @Autowired
    lateinit var template: R2dbcEntityTemplate

    suspend fun getById(id: Long): WareInfo? {
        return wareInfoDao.findById(id)
    }

    suspend fun saveOrUpdate(wareInfo: WareInfo): WareInfo {
        return wareInfoDao.save(wareInfo)
    }

    suspend fun deleteById(id: Long) {
        wareInfoDao.deleteById(id)
    }

    fun getAll(): Flow<WareInfo> {
        return wareInfoDao.findAll()
    }

    suspend fun getPagination(page: Int, limit: Int, key: String?): Map<String, Any> {
        val pageRequest = PageRequest.of(page, limit)
        var criteria = Criteria.empty()
        if (key?.isNotBlank() == true) {
            criteria = criteria.and("name").like("%${key}%")
        }
        val list = template.select<WareInfo>()
            .matching(Query.query(criteria).with(pageRequest))
            .flow().toList()
        val count = template.count(Query.query(criteria), WareInfo::class.java).awaitSingle()
        return mutableMapOf<String, Any>().apply {
            this["list"] = list
            this["totalCount"] = count
        }
    }
}

