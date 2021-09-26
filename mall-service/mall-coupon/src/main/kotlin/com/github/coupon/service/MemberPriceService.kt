package com.github.coupon.service

import com.github.coupon.dao.MemberPriceDao
import com.github.coupon.entity.MemberPrice
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-27 04:41:21
*/
@Service
class MemberPriceService {
    @Autowired
    lateinit var memberPriceDao: MemberPriceDao

    suspend fun getById(id: Long): MemberPrice? {
        return memberPriceDao.findById(id)
    }

    suspend fun saveOrUpdate(memberPrice: MemberPrice): MemberPrice {
        return memberPriceDao.save(memberPrice)
    }

    suspend fun deleteById(id: Long) {
        memberPriceDao.deleteById(id)
    }

    fun getAll(): Flow<MemberPrice> {
        return memberPriceDao.findAll()
    }
}

