package com.github.coupon.service

import com.github.coupon.dao.CouponDao
import com.github.coupon.entity.Coupon
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
class CouponService {
    @Autowired
    lateinit var couponDao: CouponDao

    suspend fun getById(id: Long): Coupon? {
        return couponDao.findById(id)
    }

    suspend fun saveOrUpdate(coupon: Coupon): Coupon {
        return couponDao.save(coupon)
    }

    suspend fun deleteById(id: Long) {
        couponDao.deleteById(id)
    }

    fun getAll(): Flow<Coupon> {
        return couponDao.findAll()
    }
}

