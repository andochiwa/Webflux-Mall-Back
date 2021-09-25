package com.github.order.service

import com.github.order.dao.OrderReturnApplyDao
import com.github.order.entity.OrderReturnApply
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
class OrderReturnApplyService {
    @Autowired
    lateinit var orderReturnApplyDao: OrderReturnApplyDao

    suspend fun getById(id: Long): OrderReturnApply? {
        return orderReturnApplyDao.findById(id)
    }

    suspend fun saveOrUpdate(orderReturnApply: OrderReturnApply): OrderReturnApply {
        return orderReturnApplyDao.save(orderReturnApply)
    }

    suspend fun deleteById(id: Long) {
        orderReturnApplyDao.deleteById(id)
    }

    fun getAll(): Flow<OrderReturnApply> {
        return orderReturnApplyDao.findAll()
    }
}

