package com.github.ware.service

import com.github.ware.dao.WareOrderTaskDetailDao
import com.github.ware.entity.WareOrderTaskDetail
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
class WareOrderTaskDetailService {
    @Autowired
    lateinit var wareOrderTaskDetailDao: WareOrderTaskDetailDao

    suspend fun getById(id: Long): WareOrderTaskDetail? {
        return wareOrderTaskDetailDao.findById(id)
    }

    suspend fun saveOrUpdate(wmsWareOrderTaskDetail: WareOrderTaskDetail): WareOrderTaskDetail {
        return wareOrderTaskDetailDao.save(wmsWareOrderTaskDetail)
    }

    suspend fun deleteById(id: Long) {
        wareOrderTaskDetailDao.deleteById(id)
    }

    fun getAll(): Flow<WareOrderTaskDetail> {
        return wareOrderTaskDetailDao.findAll()
    }
}

