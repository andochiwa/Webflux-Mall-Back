package com.github.member.service

import com.github.member.dao.MemberLoginLogDao
import com.github.member.entity.MemberLoginLog
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
class MemberLoginLogService {
    @Autowired
    lateinit var memberLoginLogDao: MemberLoginLogDao

    suspend fun getById(id: Long): MemberLoginLog? {
        return memberLoginLogDao.findById(id)
    }

    suspend fun saveOrUpdate(memberLoginLog: MemberLoginLog): MemberLoginLog {
        return memberLoginLogDao.save(memberLoginLog)
    }

    suspend fun deleteById(id: Long) {
        memberLoginLogDao.deleteById(id)
    }

    fun getAll(): Flow<MemberLoginLog> {
        return memberLoginLogDao.findAll()
    }
}

