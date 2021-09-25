package com.github.ware.service

import com.github.ware.dao.WmsWareOrderTaskDao
import com.github.ware.entity.WmsWareOrderTask
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
class WmsWareOrderTaskService {
    @Autowired
    lateinit var wmsWareOrderTaskDao: WmsWareOrderTaskDao

    suspend fun getById(id: Long): WmsWareOrderTask? {
        return wmsWareOrderTaskDao.findById(id)
    }

    suspend fun saveOrUpdate(wmsWareOrderTask: WmsWareOrderTask): WmsWareOrderTask {
        return wmsWareOrderTaskDao.save(wmsWareOrderTask)
    }

    suspend fun deleteById(id: Long) {
        wmsWareOrderTaskDao.deleteById(id)
    }

    fun getAll(): Flow<WmsWareOrderTask> {
        return wmsWareOrderTaskDao.findAll()
    }
}

