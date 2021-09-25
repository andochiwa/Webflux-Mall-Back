package com.github.member.service

import com.github.member.dao.GrowthChangeHistoryDao
import com.github.member.entity.GrowthChangeHistory
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-26 03:43:36
*/
@Service
class GrowthChangeHistoryService {
    @Autowired
    lateinit var growthChangeHistoryDao: GrowthChangeHistoryDao

    suspend fun getById(id: Long): GrowthChangeHistory? {
        return growthChangeHistoryDao.findById(id)
    }

    suspend fun saveOrUpdate(growthChangeHistory: GrowthChangeHistory): GrowthChangeHistory {
        return growthChangeHistoryDao.save(growthChangeHistory)
    }

    suspend fun deleteById(id: Long) {
        growthChangeHistoryDao.deleteById(id)
    }

    fun getAll(): Flow<GrowthChangeHistory> {
        return growthChangeHistoryDao.findAll()
    }
}

