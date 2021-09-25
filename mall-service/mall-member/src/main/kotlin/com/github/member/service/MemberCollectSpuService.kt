package com.github.member.service

import com.github.member.dao.MemberCollectSpuDao
import com.github.member.entity.MemberCollectSpu
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
class MemberCollectSpuService {
    @Autowired
    lateinit var memberCollectSpuDao: MemberCollectSpuDao

    suspend fun getById(id: Long): MemberCollectSpu? {
        return memberCollectSpuDao.findById(id)
    }

    suspend fun saveOrUpdate(memberCollectSpu: MemberCollectSpu): MemberCollectSpu {
        return memberCollectSpuDao.save(memberCollectSpu)
    }

    suspend fun deleteById(id: Long) {
        memberCollectSpuDao.deleteById(id)
    }

    fun getAll(): Flow<MemberCollectSpu> {
        return memberCollectSpuDao.findAll()
    }
}

