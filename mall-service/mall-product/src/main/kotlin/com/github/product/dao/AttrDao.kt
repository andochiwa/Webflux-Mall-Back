package com.github.product.dao

import com.github.constant.AttrEnum
import com.github.product.entity.Attr
import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.Pageable
import org.springframework.data.r2dbc.repository.Query
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

    fun findAllByAttrIdNotInAndCatelogIdAndAttrType(
        attrId: Collection<Long>, catelogId: Long, pageable: Pageable, attrType: Int = AttrEnum.ATTR_TYPE_BASE.value
    ): Flow<Attr>

    fun findAllByAttrIdNotInAndCatelogIdAndAttrNameContainingAndAttrType(
        attrId: Collection<Long>, catelogId: Long, attrName: String, page: Pageable, attrType: Int = AttrEnum.ATTR_TYPE_BASE.value
    ): Flow<Attr>

    suspend fun countAllByAttrIdNotInAndCatelogIdAndAttrType(
        attrId: Collection<Long>, catelogId: Long, attrType: Int = AttrEnum.ATTR_TYPE_BASE.value
    ): Long

    suspend fun countAllByAttrIdNotInAndCatelogIdAndAttrNameContainingAndAttrType(
        attrId: Collection<Long>, catelogId: Long, attrName: String, attrType: Int = AttrEnum.ATTR_TYPE_BASE.value
    ): Long

    @Query("select attr_id from pms_attr where attr_id in(:attrId) and search_type = :searchType;")
    fun getAllByAttrIdInAndSearchType(attrId: List<Long>, searchType: Int): Flow<Long>
}
