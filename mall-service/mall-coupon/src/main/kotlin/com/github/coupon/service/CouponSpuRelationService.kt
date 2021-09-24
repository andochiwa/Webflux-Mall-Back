package com.github.coupon.service

import com.github.coupon.dao.CouponSpuRelationDao
import com.github.coupon.entity.CouponSpuRelation
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
class CouponSpuRelationService {
    @Autowired
    lateinit var couponSpuRelationDao: CouponSpuRelationDao

    suspend fun getById(id: Long): CouponSpuRelation? {
        return couponSpuRelationDao.findById(id)
    }

    suspend fun saveOrUpdate(couponSpuRelation: CouponSpuRelation): CouponSpuRelation {
        return couponSpuRelationDao.save(couponSpuRelation)
    }

    suspend fun deleteById(id: Long) {
        couponSpuRelationDao.deleteById(id)
    }

    fun getAll(): Flow<CouponSpuRelation> {
        return couponSpuRelationDao.findAll()
    }
}

