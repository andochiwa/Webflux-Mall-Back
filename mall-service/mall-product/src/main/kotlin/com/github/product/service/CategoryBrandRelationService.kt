package com.github.product.service

import com.github.product.dao.BrandDao
import com.github.product.dao.CategoryBrandRelationDao
import com.github.product.dao.CategoryDao
import com.github.product.dto.BrandDto
import com.github.product.entity.Brand
import com.github.product.entity.Category
import com.github.product.entity.CategoryBrandRelation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
class CategoryBrandRelationService {
    @Autowired
    lateinit var categoryBrandRelationDao: CategoryBrandRelationDao

    @Autowired
    lateinit var brandDao: BrandDao

    @Autowired
    lateinit var categoryDao: CategoryDao

    suspend fun getById(id: Long): CategoryBrandRelation? {
        return categoryBrandRelationDao.findById(id)
    }

    suspend fun saveOrUpdate(categoryBrandRelation: CategoryBrandRelation): CategoryBrandRelation {
        // get brand name and category name
        val brand = brandDao.findBrandNameById(categoryBrandRelation.brandId!!)
        val category = categoryDao.findCatelogNameById(categoryBrandRelation.catelogId!!)
        categoryBrandRelation.brandName = brand.name
        categoryBrandRelation.catelogName = category.name
        return categoryBrandRelationDao.save(categoryBrandRelation)
    }

    suspend fun deleteById(id: Long) {
        categoryBrandRelationDao.deleteById(id)
    }

    fun getAll(): Flow<CategoryBrandRelation> {
        return categoryBrandRelationDao.findAll()
    }

    suspend fun deleteByIds(ids: List<Long>) {
        categoryBrandRelationDao.deleteAllById(ids)
    }

    fun getCatelogByBranId(brandId: Long): Flow<CategoryBrandRelation> {
        return categoryBrandRelationDao.findByBrandId(brandId)
    }

    suspend fun updateBrandNameByBrandId(brand: Brand) {
        categoryBrandRelationDao.updateBrandNameByBrandId(brand.name!!, brand.id!!)
    }

    suspend fun deleteByBrandId(brandIds: List<Long>) {
        categoryBrandRelationDao.deleteAllByBrandIds(brandIds)
    }

    suspend fun deleteByCategoryIds(categoryIds: List<Long>) {
        categoryBrandRelationDao.deleteAllByCategorIds(categoryIds)
    }

    fun updateCategoryNameByCategoryId(category: Category) {
        categoryBrandRelationDao.updateCategoryNameByCategoryId(category.name!!, category.id!!)
    }

    suspend fun getBrandByCatelog(catelogId: Long): List<BrandDto> {
        val brandIds = categoryBrandRelationDao.findByCatelogId(catelogId)
            .map { it.brandId!! }
        return brandDao.findAllById(brandIds)
            .map { BrandDto(it.id, it.name) }
            .toList()
    }
}

