package com.github.product.service

import com.github.product.dao.CommentReplayDao
import com.github.product.entity.CommentReplay
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
class CommentReplayService {
    @Autowired
    lateinit var commentReplayDao: CommentReplayDao

    suspend fun getById(id: Long): CommentReplay? {
        return commentReplayDao.findById(id)
    }

    suspend fun saveOrUpdate(commentReplay: CommentReplay): CommentReplay {
        return commentReplayDao.save(commentReplay)
    }

    suspend fun deleteById(id: Long) {
        commentReplayDao.deleteById(id)
    }

    fun getAll(): Flow<CommentReplay> {
        return commentReplayDao.findAll()
    }
}

