package com.github.product.dao

import com.github.product.entity.AttrGroupRelation
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
interface AttrGroupRelationDao : CoroutineCrudRepository<AttrGroupRelation, Long> {

    fun getAllByAttrGroupId(attrGroupId: Long): Flow<AttrGroupRelation>
}
