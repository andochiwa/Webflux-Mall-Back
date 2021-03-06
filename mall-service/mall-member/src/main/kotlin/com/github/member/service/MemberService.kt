package com.github.member.service

import com.github.member.dao.MemberDao
import com.github.member.entity.Member
import com.github.member.exception.EmailExistException
import com.github.member.exception.UsernameExistException
import com.github.member.vo.MemberLoginVo
import com.github.to.UserRegisterTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.r2dbc.core.select
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:43:36
 */
@Service
class MemberService {
    @Autowired
    lateinit var memberDao: MemberDao

    @Autowired
    lateinit var template: R2dbcEntityTemplate

    suspend fun getById(id: Long): Member? {
        return memberDao.findById(id)
    }

    suspend fun saveOrUpdate(member: Member): Member {
        member.id ?: run { member.createTime = LocalDateTime.now() }
        return memberDao.save(member)
    }

    suspend fun deleteByIds(ids: List<Long>) {
        memberDao.deleteAllById(ids)
    }

    fun getAll(): Flow<Member> {
        return memberDao.findAll()
    }

    suspend fun getPagination(page: Int, limit: Int, key: String?): Map<String, Any> {
        var criteria = Criteria.empty()
        key?.run {
            criteria = criteria.and("username").like("%${this}%")
                .or("nickname").like("%${this}%")
        }
        val pageRequest = PageRequest.of(page, limit)
        val list = template.select<Member>()
            .matching(Query.query(criteria).with(pageRequest))
            .flow().toList()
        val count = template.count(Query.query(criteria), Member::class.java)
            .awaitSingle()
        return mutableMapOf<String, Any>().apply {
            this["list"] = list
            this["totalCount"] = count
        }
    }

    suspend fun register(userRegisterTo: UserRegisterTo) {
        checkUsernameExist(userRegisterTo.username)
        checkEmailExist(userRegisterTo.email)
        val member = Member().apply {
            username = userRegisterTo.username
            password = BCryptPasswordEncoder().encode(userRegisterTo.password)
            email = userRegisterTo.email
        }

        memberDao.save(member)
    }

    private suspend fun checkUsernameExist(username: String) {
        if (memberDao.existsByUsername(username)) {
            throw UsernameExistException()
        }
    }

    private suspend fun checkEmailExist(email: String) {
        if (memberDao.existsByEmail(email)) {
            throw EmailExistException()
        }
    }

    suspend fun login(memberLoginVo: MemberLoginVo): Boolean {
        val member = memberDao.findByUsernameOrEmail(memberLoginVo.account!!, memberLoginVo.account!!) ?: return false
        return BCryptPasswordEncoder().matches(memberLoginVo.password, member.password)
    }
}

