package com.github.ware.service

import com.github.ware.dao.WareInfoDao
import com.github.ware.entity.WareInfo
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
class WareInfoService {
    @Autowired
    lateinit var wareInfoDao: WareInfoDao

    suspend fun getById(id: Long): WareInfo? {
        return wareInfoDao.findById(id)
    }

    suspend fun saveOrUpdate(wareInfo: WareInfo): WareInfo {
        return wareInfoDao.save(wareInfo)
    }

    suspend fun deleteById(id: Long) {
        wareInfoDao.deleteById(id)
    }

    fun getAll(): Flow<WareInfo> {
        return wareInfoDao.findAll()
    }
}

