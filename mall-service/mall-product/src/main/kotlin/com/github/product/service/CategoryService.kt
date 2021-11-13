package com.github.product.service

import cn.hutool.core.util.RandomUtil
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.github.product.dao.CategoryDao
import com.github.product.entity.Category
import com.github.product.vo.Catelog2Vo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.redisson.api.RedissonReactiveClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

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

    @Autowired
    lateinit var categoryBrandRelationService: CategoryBrandRelationService

    @Autowired
    lateinit var redisTemplate: ReactiveRedisTemplate<String, Any>

    @Autowired
    lateinit var redisson: RedissonReactiveClient

    suspend fun getById(id: Long): Category? {
        return categoryDao.findById(id)
    }

    suspend fun saveOrUpdate(category: Category): Category {
        return categoryDao.save(category)
    }

    @Transactional
    suspend fun deleteById(id: Long) {
        // todo: check references
        categoryDao.softDelete(id)
        // todo: delete redundant data from other tables
        categoryBrandRelationService.deleteByCategoryIds(listOf(id))
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
            .sortedWith { menu1, menu2 -> menu1.sort - menu2.sort }
    }

    @Transactional
    suspend fun deleteByIds(ids: List<Long>) {
        // todo: check references
        categoryDao.softDeleteAll(ids)
        // todo: delete redundant data from other tables
        categoryBrandRelationService.deleteByCategoryIds(ids)
    }

    suspend fun getCatelogPath(id: Long): List<String>? {
        val catelogPaths = mutableListOf<String>()
        getParentPath(id, catelogPaths)
        catelogPaths.reverse()
        return catelogPaths
    }

    private suspend fun getParentPath(id: Long, catelogPaths: MutableList<String>) {
        catelogPaths.add(id.toString())
        val category = this.getById(id)
        category?.parentCid?.run {
            if (this != 0L) {
                getParentPath(category.parentCid ?: return@run, catelogPaths)
            }
        }
    }

    @Transactional
    suspend fun updateDetails(category: Category) {
        categoryDao.save(category)
        // todo: update redundant data from other tables
        categoryBrandRelationService.updateCategoryNameByCategoryId(category)
    }

    suspend fun getLevel1Category(): List<Category> {
        return categoryDao.getAllByCatLevel(1).toList()
    }

    suspend fun getCatelogJson(): Map<String, List<Catelog2Vo>> {
        val valueOperation = redisTemplate.opsForValue()

        valueOperation.getAndAwait("catelogJson")?.run {
            return ObjectMapper().convertValue(this)
        }
        // coroutine will switch threads to cause thread id change
        val threadId = RandomUtil.randomLong()

        val lock = redisson.getSpinLock("catelogJson-lock")
        lock.lock(threadId).awaitSingleOrNull()
        try {
            valueOperation.getAndAwait("catelogJson")?.run {
                return ObjectMapper().convertValue(this)
            }
            val resultMap = getCatelogJsonImpl()
            valueOperation.setAndAwait("catelogJson", resultMap, Duration.ofMinutes(30))
            return resultMap
        } finally {
            lock.unlock(threadId).awaitSingleOrNull()
        }
    }

    private suspend fun getCatelogJsonImpl(): Map<String, List<Catelog2Vo>> {
        // 查出一级分类
        val level1Category = getLevel1Category()

        return level1Category.associateBy(
            { it.id.toString() },
            { category1 ->
                // 查出二级分类
                categoryDao.getAllByParentCid(category1.id!!).map { category2 ->
                    Catelog2Vo().apply {
                        id = category2.catId
                        name = category2.name
                        catelog1Id = category1.id
                        // 查询三级分类
                        catelog3List = categoryDao.getAllByParentCid(category2.id!!).map { category3 ->
                            Catelog2Vo.Catelog3Vo().apply {
                                id = category3.id
                                name = category3.name
                                catelog2Id = category2.id
                            }
                        }.toList()
                    }
                }.toList()
            }
        )
    }


}

