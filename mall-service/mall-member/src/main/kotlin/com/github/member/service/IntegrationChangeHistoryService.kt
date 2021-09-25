package com.github.member.service

import com.github.member.dao.IntegrationChangeHistoryDao
import com.github.member.entity.IntegrationChangeHistory
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
class IntegrationChangeHistoryService {
    @Autowired
    lateinit var integrationChangeHistoryDao: IntegrationChangeHistoryDao

    suspend fun getById(id: Long): IntegrationChangeHistory? {
        return integrationChangeHistoryDao.findById(id)
    }

    suspend fun saveOrUpdate(integrationChangeHistory: IntegrationChangeHistory): IntegrationChangeHistory {
        return integrationChangeHistoryDao.save(integrationChangeHistory)
    }

    suspend fun deleteById(id: Long) {
        integrationChangeHistoryDao.deleteById(id)
    }

    fun getAll(): Flow<IntegrationChangeHistory> {
        return integrationChangeHistoryDao.findAll()
    }
}

