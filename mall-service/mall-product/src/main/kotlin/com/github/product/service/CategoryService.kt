package com.github.product.service

import com.github.product.dao.CategoryDao
import com.github.product.entity.Category
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
class CategoryService {
    @Autowired
    lateinit var categoryDao: CategoryDao

    suspend fun getById(id: Long): Category? {
        return categoryDao.findById(id)
    }

    suspend fun saveOrUpdate(category: Category): Category {
        return categoryDao.save(category)
    }

    suspend fun deleteById(id: Long) {
        categoryDao.deleteById(id)
    }

    fun getAll(): Flow<Category> {
        return categoryDao.findAll()
    }
}

