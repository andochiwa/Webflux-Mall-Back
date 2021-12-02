package com.github.thirdparty.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.thirdparty.service.EmailSendService
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
 * @since 12-02-18:11
 */
@RestController
@RequestMapping("third-party/email")
@Api
class EmailSendController {

    @Autowired
    lateinit var emailSendService: EmailSendService

    @GetMapping
    @ApiOperation("send verification code to mail")
    fun sendEmailCode(@RequestParam("email") email: String): ResultDto {
        val code = emailSendService.sendEmailCode(email)
        return resultSuccess().put("verificationCode", code)
    }
}
