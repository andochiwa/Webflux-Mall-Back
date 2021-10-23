package com.github.ware.service

import com.github.ware.dao.WareOrderTaskDao
import com.github.ware.entity.WareOrderTask
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
class WareOrderTaskService {
    @Autowired
    lateinit var wareOrderTaskDao: WareOrderTaskDao

    suspend fun getById(id: Long): WareOrderTask? {
        return wareOrderTaskDao.findById(id)
    }

    suspend fun saveOrUpdate(wmsWareOrderTask: WareOrderTask): WareOrderTask {
        return wareOrderTaskDao.save(wmsWareOrderTask)
    }

    suspend fun deleteById(id: Long) {
        wareOrderTaskDao.deleteById(id)
    }

    fun getAll(): Flow<WareOrderTask> {
        return wareOrderTaskDao.findAll()
    }
}

