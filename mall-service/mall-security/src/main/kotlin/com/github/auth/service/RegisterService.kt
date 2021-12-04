package com.github.auth.service

import com.github.auth.feign.MemberFeign
import com.github.auth.feign.ThirdPartyFeign
import com.github.auth.vo.UserRegisterVo
import com.github.to.UserRegisterTo
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.deleteAndAwait
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Service
import java.time.Duration

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-02-18:46
 */
@Service
class RegisterService {

    @Autowired
    lateinit var thirdPartyFeign: ThirdPartyFeign

    @Autowired
    lateinit var redisTemplate: ReactiveRedisTemplate<String, Any>

    @Autowired
    lateinit var memberFeign: MemberFeign

    suspend fun sendCode(email: String) {
        val resultDto = thirdPartyFeign.sendEmailCode(email).awaitSingle()
        val code = resultDto.data["verificationCode"] as Int

        val valueOperation = redisTemplate.opsForValue()
        valueOperation.setAndAwait("email:code:$email", code, Duration.ofMinutes(5))

    }

    suspend fun register(userRegisterVo: UserRegisterVo) {
        val valueOperations = redisTemplate.opsForValue()
        val email = userRegisterVo.email!!
        val code = valueOperations.getAndAwait("email:code:$email") as Int?
        code ?: throw RuntimeException()
        if (code == userRegisterVo.code) {
            valueOperations.deleteAndAwait("email:code:$email")
        }
        val userRegisterTo = UserRegisterTo(userRegisterVo.username!!, userRegisterVo.password!!, email)
        val resultDto = memberFeign.register(userRegisterTo).awaitSingle()
        if (resultDto.code != 200) {
            throw RuntimeException(resultDto.data["msg"] as String)
        }
    }
}
