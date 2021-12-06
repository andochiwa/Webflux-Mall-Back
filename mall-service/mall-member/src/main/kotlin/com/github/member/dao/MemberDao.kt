package com.github.member.dao

import com.github.member.entity.Member
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:43:36
 */
interface MemberDao : CoroutineCrudRepository<Member, Long> {
    suspend fun existsByEmail(email: String): Boolean

    suspend fun existsByUsername(username: String): Boolean

    suspend fun findByUsernameOrEmail(username: String, email: String): Member?
}
