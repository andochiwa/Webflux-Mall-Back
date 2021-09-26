package com.github.product.service

import com.github.product.dao.ProductAttrValueDao
import com.github.product.entity.ProductAttrValue
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
class ProductAttrValueService {
    @Autowired
    lateinit var productAttrValueDao: ProductAttrValueDao

    suspend fun getById(id: Long): ProductAttrValue? {
        return productAttrValueDao.findById(id)
    }

    suspend fun saveOrUpdate(productAttrValue: ProductAttrValue): ProductAttrValue {
        return productAttrValueDao.save(productAttrValue)
    }

    suspend fun deleteById(id: Long) {
        productAttrValueDao.deleteById(id)
    }

    fun getAll(): Flow<ProductAttrValue> {
        return productAttrValueDao.findAll()
    }
}

