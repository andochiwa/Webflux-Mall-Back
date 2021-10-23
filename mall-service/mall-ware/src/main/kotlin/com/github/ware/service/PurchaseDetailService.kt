package com.github.ware.service

import com.github.ware.dao.PurchaseDetailDao
import com.github.ware.entity.PurchaseDetail
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-26 03:53:24
*/
@Service
class PurchaseDetailService {
    @Autowired
    lateinit var purchaseDetailDao: PurchaseDetailDao

    suspend fun getById(id: Long): PurchaseDetail? {
        return purchaseDetailDao.findById(id)
    }

    suspend fun saveOrUpdate(purchaseDetail: PurchaseDetail): PurchaseDetail {
        return purchaseDetailDao.save(purchaseDetail)
    }

    suspend fun deleteById(id: Long) {
        purchaseDetailDao.deleteById(id)
    }

    fun getAll(): Flow<PurchaseDetail> {
        return purchaseDetailDao.findAll()
    }
}

