package com.github.product.service

import com.github.product.dao.CategoryBrandRelationDao
import com.github.product.entity.CategoryBrandRelation
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
class CategoryBrandRelationService {
    @Autowired
    lateinit var categoryBrandRelationDao: CategoryBrandRelationDao

    suspend fun getById(id: Long): CategoryBrandRelation? {
        return categoryBrandRelationDao.findById(id)
    }

    suspend fun saveOrUpdate(categoryBrandRelation: CategoryBrandRelation): CategoryBrandRelation {
        return categoryBrandRelationDao.save(categoryBrandRelation)
    }

    suspend fun deleteById(id: Long) {
        categoryBrandRelationDao.deleteById(id)
    }

    fun getAll(): Flow<CategoryBrandRelation> {
        return categoryBrandRelationDao.findAll()
    }
}

