package com.github.product.service

import com.github.product.dao.SkuInfoDao
import com.github.product.entity.SkuInfo
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
class SkuInfoService {
    @Autowired
    lateinit var skuInfoDao: SkuInfoDao

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
}

