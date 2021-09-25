package com.github.order.service

import com.github.order.dao.OrderOperateHistoryDao
import com.github.order.entity.OrderOperateHistory
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
class OrderOperateHistoryService {
    @Autowired
    lateinit var orderOperateHistoryDao: OrderOperateHistoryDao

    suspend fun getById(id: Long): OrderOperateHistory? {
        return orderOperateHistoryDao.findById(id)
    }

    suspend fun saveOrUpdate(orderOperateHistory: OrderOperateHistory): OrderOperateHistory {
        return orderOperateHistoryDao.save(orderOperateHistory)
    }

    suspend fun deleteById(id: Long) {
        orderOperateHistoryDao.deleteById(id)
    }

    fun getAll(): Flow<OrderOperateHistory> {
        return orderOperateHistoryDao.findAll()
    }
}

