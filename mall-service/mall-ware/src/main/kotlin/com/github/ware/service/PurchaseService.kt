package com.github.ware.service

import com.github.ware.dao.PurchaseDao
import com.github.ware.entity.Purchase
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-26 03:53:23
*/
@Service
class PurchaseService {
    @Autowired
    lateinit var purchaseDao: PurchaseDao

    suspend fun getById(id: Long): Purchase? {
        return purchaseDao.findById(id)
    }

    suspend fun saveOrUpdate(purchase: Purchase): Purchase {
        return purchaseDao.save(purchase)
    }

    suspend fun deleteById(id: Long) {
        purchaseDao.deleteById(id)
    }

    fun getAll(): Flow<Purchase> {
        return purchaseDao.findAll()
    }
}

