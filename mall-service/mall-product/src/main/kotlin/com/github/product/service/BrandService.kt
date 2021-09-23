package com.github.product.service

import com.github.product.dao.BrandDao
import com.github.product.entity.Brand
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-24 00:47:19
*/
@Service
class BrandService {
    @Autowired
    lateinit var brandDao: BrandDao

    suspend fun getById(id: Long): Brand? {
        return brandDao.findById(id)
    }

    suspend fun saveOrUpdate(brand: Brand): Brand {
        return brandDao.save(brand)
    }

    suspend fun deleteById(id: Long) {
        brandDao.deleteById(id)
    }

    fun getAll(): Flow<Brand> {
        return brandDao.findAll()
    }
}

