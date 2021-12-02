package com.github.auth.service

import com.github.auth.feign.ThirdPartyFeign
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
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


    suspend fun sendCode(email: String) {
        val resultDto = thirdPartyFeign.sendEmailCode(email).awaitSingle()
        val code = resultDto.data["verificationCode"] as Int

        val valueOperation = redisTemplate.opsForValue()
        valueOperation.setAndAwait("email:code:$email", code, Duration.ofMinutes(5))

    }
}
