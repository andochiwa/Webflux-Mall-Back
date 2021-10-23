package com.github.ware.service

import com.github.ware.dao.WareSkuDao
import com.github.ware.entity.WareSku
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
class WareSkuService {
    @Autowired
    lateinit var wareSkuDao: WareSkuDao

    suspend fun getById(id: Long): WareSku? {
        return wareSkuDao.findById(id)
    }

    suspend fun saveOrUpdate(wmsWareSku: WareSku): WareSku {
        return wareSkuDao.save(wmsWareSku)
    }

    suspend fun deleteById(id: Long) {
        wareSkuDao.deleteById(id)
    }

    fun getAll(): Flow<WareSku> {
        return wareSkuDao.findAll()
    }
}

