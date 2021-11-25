package com.github.product.service

import com.github.product.dao.SkuInfoDao
import com.github.product.dao.SkuSaleAttrValueDao
import com.github.product.dto.SkuItemDto
import com.github.product.entity.SkuSaleAttrValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:43:25
 */
@Service
class SkuSaleAttrValueService {
    @Autowired
    lateinit var skuSaleAttrValueDao: SkuSaleAttrValueDao

    @Autowired
    lateinit var skuInfoDto: SkuInfoDao

    suspend fun getById(id: Long): SkuSaleAttrValue? {
        return skuSaleAttrValueDao.findById(id)
    }

    suspend fun saveOrUpdate(skuSaleAttrValue: SkuSaleAttrValue): SkuSaleAttrValue {
        return skuSaleAttrValueDao.save(skuSaleAttrValue)
    }

    suspend fun deleteById(id: Long) {
        skuSaleAttrValueDao.deleteById(id)
    }

    fun getAll(): Flow<SkuSaleAttrValue> {
        return skuSaleAttrValueDao.findAll()
    }

    suspend fun getSaleAttrsBySpuId(spuId: Long): List<SkuItemDto.SkuItemSaleAttrDto>? {
        val skuIds = skuInfoDto.getAllBySpuId(spuId).map { it.skuId!! }
        return skuSaleAttrValueDao.getBySkuIdIn(skuIds).toList()
            .groupBy { it.attrId!! }
            .map { (_, v) ->
                val skuItemSaleAttrDto = SkuItemDto.SkuItemSaleAttrDto()
                skuItemSaleAttrDto.attrId = v[0].attrId
                skuItemSaleAttrDto.attrName = v[0].attrName
                skuItemSaleAttrDto.attrValues = v.distinctBy { it.attrValue }.map { it.attrValue!! }
                skuItemSaleAttrDto
            }
    }
}

