package com.github.product.service

import com.github.product.dao.SpuInfoDao
import com.github.product.entity.SpuInfo
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
class SpuInfoService {
    @Autowired
    lateinit var spuInfoDao: SpuInfoDao

    suspend fun getById(id: Long): SpuInfo? {
        return spuInfoDao.findById(id)
    }

    suspend fun saveOrUpdate(spuInfo: SpuInfo): SpuInfo {
        return spuInfoDao.save(spuInfo)
    }

    suspend fun deleteById(id: Long) {
        spuInfoDao.deleteById(id)
    }

    fun getAll(): Flow<SpuInfo> {
        return spuInfoDao.findAll()
    }
}

