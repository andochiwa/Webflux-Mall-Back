package com.github.coupon.service

import com.github.coupon.dao.SeckillPromotionDao
import com.github.coupon.entity.SeckillPromotion
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-27 04:41:21
*/
@Service
class SeckillPromotionService {
    @Autowired
    lateinit var seckillPromotionDao: SeckillPromotionDao

    suspend fun getById(id: Long): SeckillPromotion? {
        return seckillPromotionDao.findById(id)
    }

    suspend fun saveOrUpdate(seckillPromotion: SeckillPromotion): SeckillPromotion {
        return seckillPromotionDao.save(seckillPromotion)
    }

    suspend fun deleteById(id: Long) {
        seckillPromotionDao.deleteById(id)
    }

    fun getAll(): Flow<SeckillPromotion> {
        return seckillPromotionDao.findAll()
    }
}

