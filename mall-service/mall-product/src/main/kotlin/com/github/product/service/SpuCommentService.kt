package com.github.product.service

import com.github.product.dao.SpuCommentDao
import com.github.product.entity.SpuComment
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
*
* @author Andochiwa
* @email a1066079469@gmail.com
* @date 2021-09-27 04:43:25
*/
@Service
class SpuCommentService {
    @Autowired
    lateinit var spuCommentDao: SpuCommentDao

    suspend fun getById(id: Long): SpuComment? {
        return spuCommentDao.findById(id)
    }

    suspend fun saveOrUpdate(spuComment: SpuComment): SpuComment {
        return spuCommentDao.save(spuComment)
    }

    suspend fun deleteById(id: Long) {
        spuCommentDao.deleteById(id)
    }

    fun getAll(): Flow<SpuComment> {
        return spuCommentDao.findAll()
    }
}

