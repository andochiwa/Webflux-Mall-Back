package com.github.product.dao

import com.github.product.entity.AttrGroup
import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
interface AttrGroupDao : CoroutineCrudRepository<AttrGroup, Long> {
    fun findBy(pageRequest: PageRequest): Flow<AttrGroup>

    fun findByAttrGroupNameContaining(key: String, pageRequest: PageRequest): Flow<AttrGroup>

    fun findByCatelogId(catelogId: Long, pageRequest: PageRequest): Flow<AttrGroup>

    fun findByAttrGroupNameContainingAndCatelogId(
        attrGroupName: String,
        CatelogId: Long,
        pageRequest: PageRequest
    ): Flow<AttrGroup>

    suspend fun countByCatelogId(catelogId: Long): Long

}
