package com.github.coupon.service

import com.github.coupon.dao.SeckillSkuNoticeDao
import com.github.coupon.entity.SeckillSkuNotice
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
class SeckillSkuNoticeService {
    @Autowired
    lateinit var seckillSkuNoticeDao: SeckillSkuNoticeDao

    suspend fun getById(id: Long): SeckillSkuNotice? {
        return seckillSkuNoticeDao.findById(id)
    }

    suspend fun saveOrUpdate(seckillSkuNotice: SeckillSkuNotice): SeckillSkuNotice {
        return seckillSkuNoticeDao.save(seckillSkuNotice)
    }

    suspend fun deleteById(id: Long) {
        seckillSkuNoticeDao.deleteById(id)
    }

    fun getAll(): Flow<SeckillSkuNotice> {
        return seckillSkuNoticeDao.findAll()
    }
}

