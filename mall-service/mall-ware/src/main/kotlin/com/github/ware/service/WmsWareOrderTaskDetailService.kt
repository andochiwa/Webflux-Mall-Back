package com.github.ware.service

import com.github.ware.dao.WmsWareOrderTaskDetailDao
import com.github.ware.entity.WmsWareOrderTaskDetail
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
class WmsWareOrderTaskDetailService {
    @Autowired
    lateinit var wmsWareOrderTaskDetailDao: WmsWareOrderTaskDetailDao

    suspend fun getById(id: Long): WmsWareOrderTaskDetail? {
        return wmsWareOrderTaskDetailDao.findById(id)
    }

    suspend fun saveOrUpdate(wmsWareOrderTaskDetail: WmsWareOrderTaskDetail): WmsWareOrderTaskDetail {
        return wmsWareOrderTaskDetailDao.save(wmsWareOrderTaskDetail)
    }

    suspend fun deleteById(id: Long) {
        wmsWareOrderTaskDetailDao.deleteById(id)
    }

    fun getAll(): Flow<WmsWareOrderTaskDetail> {
        return wmsWareOrderTaskDetailDao.findAll()
    }
}

