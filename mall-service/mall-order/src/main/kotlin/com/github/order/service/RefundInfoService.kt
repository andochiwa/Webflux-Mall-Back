package com.github.order.service

import com.github.order.dao.RefundInfoDao
import com.github.order.entity.RefundInfo
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-26 03:18:13
*/
@Service
class RefundInfoService {
    @Autowired
    lateinit var refundInfoDao: RefundInfoDao

    suspend fun getById(id: Long): RefundInfo? {
        return refundInfoDao.findById(id)
    }

    suspend fun saveOrUpdate(refundInfo: RefundInfo): RefundInfo {
        return refundInfoDao.save(refundInfo)
    }

    suspend fun deleteById(id: Long) {
        refundInfoDao.deleteById(id)
    }

    fun getAll(): Flow<RefundInfo> {
        return refundInfoDao.findAll()
    }
}

