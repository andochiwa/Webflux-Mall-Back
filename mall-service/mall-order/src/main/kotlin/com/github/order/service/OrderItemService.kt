package com.github.order.service

import com.github.order.dao.OrderItemDao
import com.github.order.entity.OrderItem
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
class OrderItemService {
    @Autowired
    lateinit var orderItemDao: OrderItemDao

    suspend fun getById(id: Long): OrderItem? {
        return orderItemDao.findById(id)
    }

    suspend fun saveOrUpdate(orderItem: OrderItem): OrderItem {
        return orderItemDao.save(orderItem)
    }

    suspend fun deleteById(id: Long) {
        orderItemDao.deleteById(id)
    }

    fun getAll(): Flow<OrderItem> {
        return orderItemDao.findAll()
    }
}

