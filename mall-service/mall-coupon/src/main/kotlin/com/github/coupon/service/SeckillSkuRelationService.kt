package com.github.coupon.service

import com.github.coupon.dao.SeckillSkuRelationDao
import com.github.coupon.entity.SeckillSkuRelation
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
class SeckillSkuRelationService {
    @Autowired
    lateinit var seckillSkuRelationDao: SeckillSkuRelationDao

    suspend fun getById(id: Long): SeckillSkuRelation? {
        return seckillSkuRelationDao.findById(id)
    }

    suspend fun saveOrUpdate(seckillSkuRelation: SeckillSkuRelation): SeckillSkuRelation {
        return seckillSkuRelationDao.save(seckillSkuRelation)
    }

    suspend fun deleteById(id: Long) {
        seckillSkuRelationDao.deleteById(id)
    }

    fun getAll(): Flow<SeckillSkuRelation> {
        return seckillSkuRelationDao.findAll()
    }
}

