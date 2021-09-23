package com.github.product.service

import com.github.product.dao.SpuImagesDao
import com.github.product.entity.SpuImages
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
class SpuImagesService {
    @Autowired
    lateinit var spuImagesDao: SpuImagesDao

    suspend fun getById(id: Long): SpuImages? {
        return spuImagesDao.findById(id)
    }

    suspend fun saveOrUpdate(spuImages: SpuImages): SpuImages {
        return spuImagesDao.save(spuImages)
    }

    suspend fun deleteById(id: Long) {
        spuImagesDao.deleteById(id)
    }

    fun getAll(): Flow<SpuImages> {
        return spuImagesDao.findAll()
    }
}

