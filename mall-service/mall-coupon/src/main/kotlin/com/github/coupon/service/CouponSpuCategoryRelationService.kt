package com.github.coupon.service

import com.github.coupon.dao.CouponSpuCategoryRelationDao
import com.github.coupon.entity.CouponSpuCategoryRelation
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
class CouponSpuCategoryRelationService {
    @Autowired
    lateinit var couponSpuCategoryRelationDao: CouponSpuCategoryRelationDao

    suspend fun getById(id: Long): CouponSpuCategoryRelation? {
        return couponSpuCategoryRelationDao.findById(id)
    }

    suspend fun saveOrUpdate(couponSpuCategoryRelation: CouponSpuCategoryRelation): CouponSpuCategoryRelation {
        return couponSpuCategoryRelationDao.save(couponSpuCategoryRelation)
    }

    suspend fun deleteById(id: Long) {
        couponSpuCategoryRelationDao.deleteById(id)
    }

    fun getAll(): Flow<CouponSpuCategoryRelation> {
        return couponSpuCategoryRelationDao.findAll()
    }
}

