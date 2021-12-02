package com.github.auth.feign

import com.github.dto.ResultDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-02-18:47
 */
@ReactiveFeignClient("mall-third-party")
interface ThirdPartyFeign {

    @GetMapping("third-party/email")
    fun sendEmailCode(@RequestParam("email") email: String): Mono<ResultDto>
}
