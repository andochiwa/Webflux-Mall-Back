package com.github.order.service

import com.github.order.dao.OrderReturnReasonDao
import com.github.order.entity.OrderReturnReason
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-26 03:18:14
*/
@Service
class OrderReturnReasonService {
    @Autowired
    lateinit var orderReturnReasonDao: OrderReturnReasonDao

    suspend fun getById(id: Long): OrderReturnReason? {
        return orderReturnReasonDao.findById(id)
    }

    suspend fun saveOrUpdate(orderReturnReason: OrderReturnReason): OrderReturnReason {
        return orderReturnReasonDao.save(orderReturnReason)
    }

    suspend fun deleteById(id: Long) {
        orderReturnReasonDao.deleteById(id)
    }

    fun getAll(): Flow<OrderReturnReason> {
        return orderReturnReasonDao.findAll()
    }
}

