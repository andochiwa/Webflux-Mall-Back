package com.github.member.service

import com.github.member.dao.MemberLevelDao
import com.github.member.entity.MemberLevel
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
class MemberLevelService {
    @Autowired
    lateinit var memberLevelDao: MemberLevelDao

    suspend fun getById(id: Long): MemberLevel? {
        return memberLevelDao.findById(id)
    }

    suspend fun saveOrUpdate(memberLevel: MemberLevel): MemberLevel {
        return memberLevelDao.save(memberLevel)
    }

    suspend fun deleteById(id: Long) {
        memberLevelDao.deleteById(id)
    }

    fun getAll(): Flow<MemberLevel> {
        return memberLevelDao.findAll()
    }
}

