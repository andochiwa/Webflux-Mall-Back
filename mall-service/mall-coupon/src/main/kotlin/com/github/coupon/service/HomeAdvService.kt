package com.github.coupon.service

import com.github.coupon.dao.HomeAdvDao
import com.github.coupon.entity.HomeAdv
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-24 23:39:59
*/
@Service
class HomeAdvService {
    @Autowired
    lateinit var homeAdvDao: HomeAdvDao

    suspend fun getById(id: Long): HomeAdv? {
        return homeAdvDao.findById(id)
    }

    suspend fun saveOrUpdate(homeAdv: HomeAdv): HomeAdv {
        return homeAdvDao.save(homeAdv)
    }

    suspend fun deleteById(id: Long) {
        homeAdvDao.deleteById(id)
    }

    fun getAll(): Flow<HomeAdv> {
        return homeAdvDao.findAll()
    }
}

