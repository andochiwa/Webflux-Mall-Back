package com.github.product.service

import com.github.product.dao.SkuSaleAttrValueDao
import com.github.product.entity.SkuSaleAttrValue
import kotlinx.coroutines.flow.Flow
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
}

