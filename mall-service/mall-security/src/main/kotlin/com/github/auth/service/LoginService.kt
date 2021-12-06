package com.github.auth.service

import com.github.auth.feign.MemberFeign
import com.github.auth.vo.UserLoginVo
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-06-21:08
 */
@Service
class LoginService {
    @Autowired
    lateinit var memberFeign: MemberFeign

    suspend fun login(userLoginVo: UserLoginVo): Boolean {
        val resultDto = memberFeign.login(userLoginVo).awaitSingle()
        return resultDto.code == 200
    }
}
