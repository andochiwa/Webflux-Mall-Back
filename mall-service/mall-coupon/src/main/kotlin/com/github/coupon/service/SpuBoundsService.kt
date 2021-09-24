package com.github.coupon.service

import com.github.coupon.dao.SpuBoundsDao
import com.github.coupon.entity.SpuBounds
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
class SpuBoundsService {
    @Autowired
    lateinit var spuBoundsDao: SpuBoundsDao

    suspend fun getById(id: Long): SpuBounds? {
        return spuBoundsDao.findById(id)
    }

    suspend fun saveOrUpdate(spuBounds: SpuBounds): SpuBounds {
        return spuBoundsDao.save(spuBounds)
    }

    suspend fun deleteById(id: Long) {
        spuBoundsDao.deleteById(id)
    }

    fun getAll(): Flow<SpuBounds> {
        return spuBoundsDao.findAll()
    }
}

