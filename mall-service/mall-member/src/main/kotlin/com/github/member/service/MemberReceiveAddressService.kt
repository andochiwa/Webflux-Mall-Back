package com.github.member.service

import com.github.member.dao.MemberReceiveAddressDao
import com.github.member.entity.MemberReceiveAddress
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
class MemberReceiveAddressService {
    @Autowired
    lateinit var memberReceiveAddressDao: MemberReceiveAddressDao

    suspend fun getById(id: Long): MemberReceiveAddress? {
        return memberReceiveAddressDao.findById(id)
    }

    suspend fun saveOrUpdate(memberReceiveAddress: MemberReceiveAddress): MemberReceiveAddress {
        return memberReceiveAddressDao.save(memberReceiveAddress)
    }

    suspend fun deleteById(id: Long) {
        memberReceiveAddressDao.deleteById(id)
    }

    fun getAll(): Flow<MemberReceiveAddress> {
        return memberReceiveAddressDao.findAll()
    }
}

