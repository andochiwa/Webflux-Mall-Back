package com.github.member.dao

import com.github.member.entity.MemberLevel
import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:43:36
 */
interface MemberLevelDao : CoroutineCrudRepository<MemberLevel, Long> {

    fun findAllBy(pageable: Pageable): Flow<MemberLevel>

    fun findAllByNameContaining(name: String, pageable: Pageable): Flow<MemberLevel>

    suspend fun countAllByNameContaining(name: String): Long
}
