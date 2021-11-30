package com.github.product.service

import cn.hutool.core.util.RandomUtil
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.github.product.dao.SkuImagesDao
import com.github.product.dao.SkuInfoDao
import com.github.product.dao.SpuInfoDescDao
import com.github.product.dto.SkuItemDto
import com.github.product.entity.SkuInfo
import com.github.product.extensions.withLock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import org.redisson.api.RedissonReactiveClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.r2dbc.core.select
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.isEqual
import org.springframework.stereotype.Service
import java.time.Duration

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

    @Autowired
    lateinit var skuImagesDao: SkuImagesDao

    @Autowired
    lateinit var spuInfoDescDao: SpuInfoDescDao

    @Autowired
    lateinit var attrGroupService: AttrGroupService

    @Autowired
    lateinit var skuSaleAttrValueService: SkuSaleAttrValueService

    @Autowired
    lateinit var redisTemplate: ReactiveRedisTemplate<String, Any>

    @Autowired
    lateinit var redisson: RedissonReactiveClient

    @Autowired
    lateinit var objectMapper: ObjectMapper

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

    suspend fun getSkuItem(skuId: Long): SkuItemDto {
        val valueOperation = redisTemplate.opsForValue()

        valueOperation.getAndAwait("skuItemJson-$skuId")?.run {
            return objectMapper.convertValue(this)
        }
        val threadId = RandomUtil.randomLong()
        val lock = redisson.getSpinLock("skuItemJson-lock-$skuId")
        lock.withLock(threadId) {
            valueOperation.getAndAwait("skuItemJson-$skuId")?.run {
                return objectMapper.convertValue(this)
            }
            val result = getSkuItemImpl(skuId)
            valueOperation.setAndAwait("skuItemJson-$skuId", result, Duration.ofHours(1))
            return result
        }
    }

    private suspend fun getSkuItemImpl(skuId: Long): SkuItemDto {
        val skuItemDto = SkuItemDto()
        // sku基本信息
        val skuInfo = skuInfoDao.findById(skuId) ?: return skuItemDto
        skuItemDto.skuInfo = skuInfo
        // sku图片信息
        skuItemDto.skuImages = skuImagesDao.findAllBySkuId(skuId).toList()
        // spu销售信息组合
        val spuId = skuInfo.spuId!!
        skuItemDto.saleAttrs = skuSaleAttrValueService.getSaleAttrsBySpuId(spuId)
        // spu介绍
        skuItemDto.spuInfoDesc = spuInfoDescDao.findBySpuId(spuId)
        // spu规格参数信息
        val catelogId = skuInfo.catelogId!!
        skuItemDto.groupAttrs = attrGroupService.getAttrGroupWithAttrsBySpuId(spuId, catelogId)
        return skuItemDto
    }
}

