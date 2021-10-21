package com.github.product.dao

import com.github.product.entity.AttrGroupRelation
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
interface AttrGroupRelationDao : CoroutineCrudRepository<AttrGroupRelation, Long> {

    fun getAllByAttrGroupId(attrGroupId: Long): Flow<AttrGroupRelation>

    @Modifying
    @Query("update pms_attr_group_relation set attr_group_id = :attrGroupId where attr_id = :attrId;")
    suspend fun updateGroup(attrGroupId: Long, attrId: Long)

    suspend fun findByAttrId(attrId: Long): AttrGroupRelation?
}
