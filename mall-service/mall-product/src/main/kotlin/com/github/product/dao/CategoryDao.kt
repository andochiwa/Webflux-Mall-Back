package com.github.product.dao

import com.github.product.entity.Category
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
interface CategoryDao : CoroutineCrudRepository<Category, Long> {

    @Modifying
    @Query("update pms_category set show_status = 0 where cat_id = :id")
    suspend fun softDelete(id: Long)

    @Modifying
    @Query("update pms_category set show_status = 0 where cat_id in (:id)")
    suspend fun softDeleteAll(id: List<Long>)

    fun findAllByShowStatus(showStatus: Int = 1): Flow<Category>

    @Query("select name from pms_category where cat_id = :id")
    fun findCatelogNameById(id: Long): Category
}
