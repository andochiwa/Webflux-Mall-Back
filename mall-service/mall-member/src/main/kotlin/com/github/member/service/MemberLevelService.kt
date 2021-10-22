package com.github.member.service

import com.github.member.dao.MemberLevelDao
import com.github.member.entity.MemberLevel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
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
        println(memberLevel)
        return memberLevelDao.save(memberLevel)
    }

    suspend fun deleteById(ids: List<Long>) {
        memberLevelDao.deleteAllById(ids)
    }

    fun getAll(): Flow<MemberLevel> {
        return memberLevelDao.findAll()
    }


    suspend fun getAllPagination(page: Int, limit: Int, key: String?): Map<String, Any> {
        val count: Long
        val resultList: List<MemberLevel>
        val pageRequest = PageRequest.of(page, limit)
        if (key == null) {
            resultList = memberLevelDao.findAllBy(pageRequest).toList()
            count = memberLevelDao.count()
        } else {
            resultList = memberLevelDao.findAllByNameContaining(key, pageRequest).toList()
            count = memberLevelDao.countAllByNameContaining(key)
        }
        return mutableMapOf<String, Any>().apply {
            this["list"] = resultList
            this["totalCount"] = count
        }
    }
}

