package com.github.coupon.service

import com.github.coupon.dao.HomeSubjectSpuDao
import com.github.coupon.entity.HomeSubjectSpu
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
class HomeSubjectSpuService {
    @Autowired
    lateinit var homeSubjectSpuDao: HomeSubjectSpuDao

    suspend fun getById(id: Long): HomeSubjectSpu? {
        return homeSubjectSpuDao.findById(id)
    }

    suspend fun saveOrUpdate(homeSubjectSpu: HomeSubjectSpu): HomeSubjectSpu {
        return homeSubjectSpuDao.save(homeSubjectSpu)
    }

    suspend fun deleteById(id: Long) {
        homeSubjectSpuDao.deleteById(id)
    }

    fun getAll(): Flow<HomeSubjectSpu> {
        return homeSubjectSpuDao.findAll()
    }
}

