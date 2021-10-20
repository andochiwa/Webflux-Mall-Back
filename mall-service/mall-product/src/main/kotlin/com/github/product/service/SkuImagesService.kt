package com.github.product.service

import com.github.product.dao.SkuImagesDao
import com.github.product.entity.SkuImages
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
class SkuImagesService {
    @Autowired
    lateinit var skuImagesDao: SkuImagesDao

    suspend fun getById(id: Long): SkuImages? {
        return skuImagesDao.findById(id)
    }

    suspend fun saveOrUpdate(skuImages: SkuImages): SkuImages {
        return skuImagesDao.save(skuImages)
    }

    suspend fun deleteById(id: Long) {
        skuImagesDao.deleteById(id)
    }

    fun getAll(): Flow<SkuImages> {
        return skuImagesDao.findAll()
    }
}

