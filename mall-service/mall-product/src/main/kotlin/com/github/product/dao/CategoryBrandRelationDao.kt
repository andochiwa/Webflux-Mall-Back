package com.github.product.dao

import com.github.product.entity.CategoryBrandRelation
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
interface CategoryBrandRelationDao : CoroutineCrudRepository<CategoryBrandRelation, Long> {

    fun findByBrandId(brandId: Long): Flow<CategoryBrandRelation>

    @Modifying
    @Query("update pms_category_brand_relation set brand_name = :brandName where brand_id = :brandId")
    suspend fun updateBrandNameByBrandId(brandName: String, brandId: Long)

    @Modifying
    @Query("delete from pms_category_brand_relation where brand_id in (:brandIds)")
    suspend fun deleteAllByBrandIds(brandIds: List<Long>)

    @Modifying
    @Query("delete from pms_category_brand_relation where catelog_id in (:categoryIds);")
    suspend fun deleteAllByCategorIds(categoryIds: List<Long>)

    @Modifying
    @Query("update pms_category_brand_relation set catelog_name = :categoryName where catelog_id = :categoryId;")
    fun updateCategoryNameByCategoryId(categoryName: String, categoryId: Long)

    fun findByCatelogId(catelogId: Long): Flow<CategoryBrandRelation>

}
