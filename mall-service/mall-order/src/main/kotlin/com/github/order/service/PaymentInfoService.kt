package com.github.order.service

import com.github.order.dao.PaymentInfoDao
import com.github.order.entity.PaymentInfo
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
class PaymentInfoService {
    @Autowired
    lateinit var paymentInfoDao: PaymentInfoDao

    suspend fun getById(id: Long): PaymentInfo? {
        return paymentInfoDao.findById(id)
    }

    suspend fun saveOrUpdate(paymentInfo: PaymentInfo): PaymentInfo {
        return paymentInfoDao.save(paymentInfo)
    }

    suspend fun deleteById(id: Long) {
        paymentInfoDao.deleteById(id)
    }

    fun getAll(): Flow<PaymentInfo> {
        return paymentInfoDao.findAll()
    }
}

