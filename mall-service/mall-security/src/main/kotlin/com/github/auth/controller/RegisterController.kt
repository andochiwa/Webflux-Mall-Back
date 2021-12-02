package com.github.auth.controller

import com.github.auth.service.RegisterService
import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-02-18:44
 */
@RestController
@RequestMapping("auth/login")
@Api
class RegisterController {

    @Autowired
    lateinit var registerService: RegisterService

    @GetMapping("email/code")
    @ApiOperation("send verification code to email")
    suspend fun sendCode(@RequestParam("email") email: String): ResultDto {
        registerService.sendCode(email)
        return resultSuccess()
    }
}
