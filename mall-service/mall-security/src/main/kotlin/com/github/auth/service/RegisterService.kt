package com.github.auth.service

import com.github.auth.feign.ThirdPartyFeign
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-02-18:46
 */
@Service
class RegisterService {

    @Autowired
    lateinit var thirdPartyFeign: ThirdPartyFeign

    suspend fun sendCode(email: String) {
        thirdPartyFeign.sendEmailCode(email).awaitSingleOrNull()
    }
}
