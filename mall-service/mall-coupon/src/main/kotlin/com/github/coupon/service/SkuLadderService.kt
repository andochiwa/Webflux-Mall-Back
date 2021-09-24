package com.github.coupon.service

import com.github.coupon.dao.SkuLadderDao
import com.github.coupon.entity.SkuLadder
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
class SkuLadderService {
    @Autowired
    lateinit var skuLadderDao: SkuLadderDao

    suspend fun getById(id: Long): SkuLadder? {
        return skuLadderDao.findById(id)
    }

    suspend fun saveOrUpdate(skuLadder: SkuLadder): SkuLadder {
        return skuLadderDao.save(skuLadder)
    }

    suspend fun deleteById(id: Long) {
        skuLadderDao.deleteById(id)
    }

    fun getAll(): Flow<SkuLadder> {
        return skuLadderDao.findAll()
    }
}

