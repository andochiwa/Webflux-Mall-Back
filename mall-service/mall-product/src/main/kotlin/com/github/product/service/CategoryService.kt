package com.github.product.service

import com.github.product.dao.CategoryDao
import com.github.product.entity.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
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
        // todo: check references
        categoryDao.softDelete(id)
    }

    fun getAll(): Flow<Category> {
        return categoryDao.findAllByShowStatus()
    }

    suspend fun listWithTree(): List<Category> {
        val categoryList = getAll().toList()
        return categoryList.filter { it.parentCid == 0L }
            .onEach { it.children = getChildren(it, categoryList) }
            .sortedWith { menu1, menu2 -> menu1.sort - menu2.sort }
    }

    private fun getChildren(root: Category, all: List<Category>): List<Category> {
        return all.filter { it.parentCid == root.id }
            .onEach { it.children = getChildren(it, all) }
            .sortedWith{ menu1, menu2 -> menu1.sort - menu2.sort }
    }

    suspend fun deleteByIds(ids: List<Long>) {
        // todo: check references
        categoryDao.softDeleteAll(ids)
    }
}

