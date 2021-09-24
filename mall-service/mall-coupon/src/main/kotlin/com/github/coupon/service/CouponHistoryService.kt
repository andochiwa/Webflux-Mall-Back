package com.github.coupon.service

import com.github.coupon.dao.CouponHistoryDao
import com.github.coupon.entity.CouponHistory
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
class CouponHistoryService {
    @Autowired
    lateinit var couponHistoryDao: CouponHistoryDao

    suspend fun getById(id: Long): CouponHistory? {
        return couponHistoryDao.findById(id)
    }

    suspend fun saveOrUpdate(couponHistory: CouponHistory): CouponHistory {
        return couponHistoryDao.save(couponHistory)
    }

    suspend fun deleteById(id: Long) {
        couponHistoryDao.deleteById(id)
    }

    fun getAll(): Flow<CouponHistory> {
        return couponHistoryDao.findAll()
    }
}

