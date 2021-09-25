package com.github.ware.service

import com.github.ware.dao.WmsPurchaseDetailDao
import com.github.ware.entity.WmsPurchaseDetail
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
class WmsPurchaseDetailService {
    @Autowired
    lateinit var wmsPurchaseDetailDao: WmsPurchaseDetailDao

    suspend fun getById(id: Long): WmsPurchaseDetail? {
        return wmsPurchaseDetailDao.findById(id)
    }

    suspend fun saveOrUpdate(wmsPurchaseDetail: WmsPurchaseDetail): WmsPurchaseDetail {
        return wmsPurchaseDetailDao.save(wmsPurchaseDetail)
    }

    suspend fun deleteById(id: Long) {
        wmsPurchaseDetailDao.deleteById(id)
    }

    fun getAll(): Flow<WmsPurchaseDetail> {
        return wmsPurchaseDetailDao.findAll()
    }
}

