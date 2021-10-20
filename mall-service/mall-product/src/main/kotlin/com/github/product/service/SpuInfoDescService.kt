package com.github.product.service

import com.github.product.dao.SpuInfoDescDao
import com.github.product.entity.SpuInfoDesc
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
class SpuInfoDescService {
    @Autowired
    lateinit var spuInfoDescDao: SpuInfoDescDao

    suspend fun getById(id: Long): SpuInfoDesc? {
        return spuInfoDescDao.findById(id)
    }

    suspend fun saveOrUpdate(spuInfoDesc: SpuInfoDesc): SpuInfoDesc {
        return spuInfoDescDao.save(spuInfoDesc)
    }

    suspend fun deleteById(id: Long) {
        spuInfoDescDao.deleteById(id)
    }

    fun getAll(): Flow<SpuInfoDesc> {
        return spuInfoDescDao.findAll()
    }
}

