package com.github.coupon.service

import com.github.coupon.dao.HomeSubjectDao
import com.github.coupon.entity.HomeSubject
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
class HomeSubjectService {
    @Autowired
    lateinit var homeSubjectDao: HomeSubjectDao

    suspend fun getById(id: Long): HomeSubject? {
        return homeSubjectDao.findById(id)
    }

    suspend fun saveOrUpdate(homeSubject: HomeSubject): HomeSubject {
        return homeSubjectDao.save(homeSubject)
    }

    suspend fun deleteById(id: Long) {
        homeSubjectDao.deleteById(id)
    }

    fun getAll(): Flow<HomeSubject> {
        return homeSubjectDao.findAll()
    }
}

