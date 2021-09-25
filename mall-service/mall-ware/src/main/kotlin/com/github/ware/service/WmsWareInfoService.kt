package com.github.ware.service

import com.github.ware.dao.WmsWareInfoDao
import com.github.ware.entity.WmsWareInfo
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
class WmsWareInfoService {
    @Autowired
    lateinit var wmsWareInfoDao: WmsWareInfoDao

    suspend fun getById(id: Long): WmsWareInfo? {
        return wmsWareInfoDao.findById(id)
    }

    suspend fun saveOrUpdate(wmsWareInfo: WmsWareInfo): WmsWareInfo {
        return wmsWareInfoDao.save(wmsWareInfo)
    }

    suspend fun deleteById(id: Long) {
        wmsWareInfoDao.deleteById(id)
    }

    fun getAll(): Flow<WmsWareInfo> {
        return wmsWareInfoDao.findAll()
    }
}

