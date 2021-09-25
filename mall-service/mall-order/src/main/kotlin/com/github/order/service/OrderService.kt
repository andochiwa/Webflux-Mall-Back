package com.github.order.service

import com.github.order.dao.OrderDao
import com.github.order.entity.Order
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
class OrderService {
    @Autowired
    lateinit var orderDao: OrderDao

    suspend fun getById(id: Long): Order? {
        return orderDao.findById(id)
    }

    suspend fun saveOrUpdate(order: Order): Order {
        return orderDao.save(order)
    }

    suspend fun deleteById(id: Long) {
        orderDao.deleteById(id)
    }

    fun getAll(): Flow<Order> {
        return orderDao.findAll()
    }
}

