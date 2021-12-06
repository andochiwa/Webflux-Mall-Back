package com.github.auth.web

import com.github.auth.service.LoginService
import com.github.auth.service.RegisterService
import com.github.auth.vo.UserLoginVo
import com.github.auth.vo.UserRegisterVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-30-22:14
 */
@Controller
@Api
class LoginWebController {

    @Autowired
    lateinit var registerService: RegisterService

    @Autowired
    lateinit var loginService: LoginService

    @GetMapping("login", "login.html")
    @ApiOperation("login page")
    fun loginPage(): String {
        return "login"
    }

    @GetMapping("register", "register.html")
    @ApiOperation("register page")
    fun register(): String {
        return "register"
    }

    @PostMapping("auth/register")
    @ApiOperation("register")
    suspend fun register(@Validated userRegisterVo: UserRegisterVo): String {
        registerService.register(userRegisterVo)
        return "redirect:/login"
    }

    @PostMapping("auth/login")
    @ApiOperation("login")
    suspend fun login(@Validated userLoginVo: UserLoginVo): String {
        val loginSuccess = loginService.login(userLoginVo)
        return if (loginSuccess) "redirect:http://chiwamall.com" else "redirect:/login"
    }
}
