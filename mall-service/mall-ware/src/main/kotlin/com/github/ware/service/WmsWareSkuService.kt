package com.github.ware.service

import com.github.ware.dao.WmsWareSkuDao
import com.github.ware.entity.WmsWareSku
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
class WmsWareSkuService {
    @Autowired
    lateinit var wmsWareSkuDao: WmsWareSkuDao

    suspend fun getById(id: Long): WmsWareSku? {
        return wmsWareSkuDao.findById(id)
    }

    suspend fun saveOrUpdate(wmsWareSku: WmsWareSku): WmsWareSku {
        return wmsWareSkuDao.save(wmsWareSku)
    }

    suspend fun deleteById(id: Long) {
        wmsWareSkuDao.deleteById(id)
    }

    fun getAll(): Flow<WmsWareSku> {
        return wmsWareSkuDao.findAll()
    }
}

