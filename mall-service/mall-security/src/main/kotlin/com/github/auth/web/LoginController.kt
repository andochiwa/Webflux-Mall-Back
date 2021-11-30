package com.github.auth.web

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-30-22:14
 */
@Controller
@Api
class LoginController {

    @GetMapping("login", "login.html")
    @ApiOperation("login page")
    fun login(): String {
        return "login"
    }

    @GetMapping("register", "register.html")
    @ApiOperation("register page")
    fun register(): String {
        return "register"
    }
}
