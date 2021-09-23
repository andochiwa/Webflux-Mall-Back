package com.github.product.service

import com.github.product.dao.AttrGroupDao
import com.github.product.entity.AttrGroup
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-24 00:47:19
*/
@Service
class AttrGroupService {
    @Autowired
    lateinit var attrGroupDao: AttrGroupDao

    suspend fun getById(id: Long): AttrGroup? {
        return attrGroupDao.findById(id)
    }

    suspend fun saveOrUpdate(attrGroup: AttrGroup): AttrGroup {
        return attrGroupDao.save(attrGroup)
    }

    suspend fun deleteById(id: Long) {
        attrGroupDao.deleteById(id)
    }

    fun getAll(): Flow<AttrGroup> {
        return attrGroupDao.findAll()
    }
}

