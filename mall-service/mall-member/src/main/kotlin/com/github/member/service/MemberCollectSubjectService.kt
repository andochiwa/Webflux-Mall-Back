package com.github.member.service

import com.github.member.dao.MemberCollectSubjectDao
import com.github.member.entity.MemberCollectSubject
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
class MemberCollectSubjectService {
    @Autowired
    lateinit var memberCollectSubjectDao: MemberCollectSubjectDao

    suspend fun getById(id: Long): MemberCollectSubject? {
        return memberCollectSubjectDao.findById(id)
    }

    suspend fun saveOrUpdate(memberCollectSubject: MemberCollectSubject): MemberCollectSubject {
        return memberCollectSubjectDao.save(memberCollectSubject)
    }

    suspend fun deleteById(id: Long) {
        memberCollectSubjectDao.deleteById(id)
    }

    fun getAll(): Flow<MemberCollectSubject> {
        return memberCollectSubjectDao.findAll()
    }
}

