package com.github.product.dao

import com.github.product.entity.Attr
import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
interface AttrDao : CoroutineCrudRepository<Attr, Long> {

    fun findByAttrTypeAndCatelogId(attrType: Int, catelogId: Long, page: Pageable): Flow<Attr>

    suspend fun countByAttrTypeAndCatelogId(attrType: Int, catelogId: Long): Long

    fun findByAttrTypeAndCatelogIdAndAttrNameContaining(
        attrType: Int,
        catelogId: Long,
        attrName: String,
        page: Pageable
    ): Flow<Attr>

    suspend fun countByAttrTypeAndCatelogIdAndAttrNameContaining(attrType: Int, catelogId: Long, attrName: String): Long

    fun findByAttrType(attrType: Int, page: Pageable): Flow<Attr>

    suspend fun countByAttrType(attrType: Int): Long

    fun findByAttrTypeAndAttrNameContaining(attrType: Int, attrName: String, page: Pageable): Flow<Attr>

    suspend fun countByAttrTypeAndAttrNameContaining(attrType: Int, attrName: String): Long
}
