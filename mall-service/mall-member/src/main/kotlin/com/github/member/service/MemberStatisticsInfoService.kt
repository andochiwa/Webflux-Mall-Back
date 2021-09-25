package com.github.member.service

import com.github.member.dao.MemberStatisticsInfoDao
import com.github.member.entity.MemberStatisticsInfo
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
class MemberStatisticsInfoService {
    @Autowired
    lateinit var memberStatisticsInfoDao: MemberStatisticsInfoDao

    suspend fun getById(id: Long): MemberStatisticsInfo? {
        return memberStatisticsInfoDao.findById(id)
    }

    suspend fun saveOrUpdate(memberStatisticsInfo: MemberStatisticsInfo): MemberStatisticsInfo {
        return memberStatisticsInfoDao.save(memberStatisticsInfo)
    }

    suspend fun deleteById(id: Long) {
        memberStatisticsInfoDao.deleteById(id)
    }

    fun getAll(): Flow<MemberStatisticsInfo> {
        return memberStatisticsInfoDao.findAll()
    }
}

