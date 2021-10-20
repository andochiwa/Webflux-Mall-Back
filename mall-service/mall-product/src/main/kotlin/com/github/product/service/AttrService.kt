package com.github.product.service

import com.github.product.dao.AttrDao
import com.github.product.entity.Attr
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
class AttrService {
    @Autowired
    lateinit var attrDao: AttrDao

    suspend fun getById(id: Long): Attr? {
        return attrDao.findById(id)
    }

    suspend fun saveOrUpdate(attr: Attr): Attr {
        return attrDao.save(attr)
    }

    suspend fun deleteById(id: Long) {
        attrDao.deleteById(id)
    }

    fun getAll(): Flow<Attr> {
        return attrDao.findAll()
    }

    fun getAllById(attrIds: List<Long>): Flow<Attr> {
        return attrDao.findAllById(attrIds)
    }
}

