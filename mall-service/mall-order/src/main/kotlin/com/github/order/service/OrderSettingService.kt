package com.github.order.service

import com.github.order.dao.OrderSettingDao
import com.github.order.entity.OrderSetting
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
class OrderSettingService {
    @Autowired
    lateinit var orderSettingDao: OrderSettingDao

    suspend fun getById(id: Long): OrderSetting? {
        return orderSettingDao.findById(id)
    }

    suspend fun saveOrUpdate(orderSetting: OrderSetting): OrderSetting {
        return orderSettingDao.save(orderSetting)
    }

    suspend fun deleteById(id: Long) {
        orderSettingDao.deleteById(id)
    }

    fun getAll(): Flow<OrderSetting> {
        return orderSettingDao.findAll()
    }
}

