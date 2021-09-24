package com.github.coupon.service

import com.github.coupon.dao.SkuFullReductionDao
import com.github.coupon.entity.SkuFullReduction
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-24 23:39:59
*/
@Service
class SkuFullReductionService {
    @Autowired
    lateinit var skuFullReductionDao: SkuFullReductionDao

    suspend fun getById(id: Long): SkuFullReduction? {
        return skuFullReductionDao.findById(id)
    }

    suspend fun saveOrUpdate(skuFullReduction: SkuFullReduction): SkuFullReduction {
        return skuFullReductionDao.save(skuFullReduction)
    }

    suspend fun deleteById(id: Long) {
        skuFullReductionDao.deleteById(id)
    }

    fun getAll(): Flow<SkuFullReduction> {
        return skuFullReductionDao.findAll()
    }
}

