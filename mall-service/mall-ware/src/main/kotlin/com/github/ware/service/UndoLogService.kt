package com.github.ware.service

import com.github.ware.dao.UndoLogDao
import com.github.ware.entity.UndoLog
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
class UndoLogService {
    @Autowired
    lateinit var undoLogDao: UndoLogDao

    suspend fun getById(id: Long): UndoLog? {
        return undoLogDao.findById(id)
    }

    suspend fun saveOrUpdate(undoLog: UndoLog): UndoLog {
        return undoLogDao.save(undoLog)
    }

    suspend fun deleteById(id: Long) {
        undoLogDao.deleteById(id)
    }

    fun getAll(): Flow<UndoLog> {
        return undoLogDao.findAll()
    }
}

