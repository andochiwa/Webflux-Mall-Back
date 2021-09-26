package com.github.coupon.service

import com.github.coupon.dao.SeckillSessionDao
import com.github.coupon.entity.SeckillSession
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
class SeckillSessionService {
    @Autowired
    lateinit var seckillSessionDao: SeckillSessionDao

    suspend fun getById(id: Long): SeckillSession? {
        return seckillSessionDao.findById(id)
    }

    suspend fun saveOrUpdate(seckillSession: SeckillSession): SeckillSession {
        return seckillSessionDao.save(seckillSession)
    }

    suspend fun deleteById(id: Long) {
        seckillSessionDao.deleteById(id)
    }

    fun getAll(): Flow<SeckillSession> {
        return seckillSessionDao.findAll()
    }
}

