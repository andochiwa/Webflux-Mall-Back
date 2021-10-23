package com.github.product.service

import com.github.product.dao.SkuInfoDao
import com.github.product.entity.SkuInfo
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
 * @date 2021-09-27 04:43:25
 */
@Service
class SkuInfoService {
    @Autowired
    lateinit var skuInfoDao: SkuInfoDao

    @Autowired
    lateinit var template: R2dbcEntityTemplate

    suspend fun getById(id: Long): SkuInfo? {
        return skuInfoDao.findById(id)
    }

    suspend fun saveOrUpdate(skuInfo: SkuInfo): SkuInfo {
        return skuInfoDao.save(skuInfo)
    }

    suspend fun deleteById(id: Long) {
        skuInfoDao.deleteById(id)
    }

    fun getAll(): Flow<SkuInfo> {
        return skuInfoDao.findAll()
    }

    suspend fun getSkusOnConditions(
        page: Int,
        limit: Int,
        key: String?,
        catelogId: Long?,
        brandId: Long?,
        min: Int?,
        max: Int?,
    ): Map<String, Any> {
        var criteria = Criteria.empty()
        if (catelogId?.equals(0L) == false) {
            criteria = criteria.and("catelogId").isEqual(catelogId)
        }
        if (brandId?.equals(0L) == false) {
            criteria = criteria.and("brand_id").isEqual(brandId)
        }
        if (key?.isNotBlank() == true) {
            criteria = criteria.and("sku_name").like("%${key}%")
        }
        min?.run { criteria = criteria.and("price").greaterThanOrEquals(min) }
        max?.run { criteria = criteria.and("price").lessThanOrEquals(max) }

        val pageRequest = PageRequest.of(page, limit)
        val list = template.select<SkuInfo>()
            .matching(Query.query(criteria).with(pageRequest))
            .flow().toList()
        val count = template.count(Query.query(criteria), SkuInfo::class.java)
            .awaitSingle()
        return mutableMapOf<String, Any>().apply {
            this["list"] = list
            this["totalCount"] = count
        }
    }
}

