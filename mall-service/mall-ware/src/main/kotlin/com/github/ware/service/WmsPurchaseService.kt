package com.github.ware.service

import com.github.ware.dao.WmsPurchaseDao
import com.github.ware.entity.WmsPurchase
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
class WmsPurchaseService {
    @Autowired
    lateinit var wmsPurchaseDao: WmsPurchaseDao

    suspend fun getById(id: Long): WmsPurchase? {
        return wmsPurchaseDao.findById(id)
    }

    suspend fun saveOrUpdate(wmsPurchase: WmsPurchase): WmsPurchase {
        return wmsPurchaseDao.save(wmsPurchase)
    }

    suspend fun deleteById(id: Long) {
        wmsPurchaseDao.deleteById(id)
    }

    fun getAll(): Flow<WmsPurchase> {
        return wmsPurchaseDao.findAll()
    }
}

