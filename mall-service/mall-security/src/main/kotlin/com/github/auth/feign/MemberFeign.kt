package com.github.auth.feign

import com.github.dto.ResultDto
import com.github.to.UserRegisterTo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-04-20:33
 */
@ReactiveFeignClient("mall-member")
interface MemberFeign {
    @PostMapping("member/member/register")
    fun register(@RequestBody userRegisterTo: UserRegisterTo): Mono<ResultDto>
}
