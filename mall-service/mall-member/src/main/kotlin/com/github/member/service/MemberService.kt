package com.github.member.service

import com.github.member.dao.MemberDao
import com.github.member.entity.Member
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
class MemberService {
    @Autowired
    lateinit var memberDao: MemberDao

    suspend fun getById(id: Long): Member? {
        return memberDao.findById(id)
    }

    suspend fun saveOrUpdate(member: Member): Member {
        return memberDao.save(member)
    }

    suspend fun deleteById(id: Long) {
        memberDao.deleteById(id)
    }

    fun getAll(): Flow<Member> {
        return memberDao.findAll()
    }
}

