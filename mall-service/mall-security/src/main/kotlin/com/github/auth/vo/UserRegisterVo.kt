package com.github.auth.vo

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-04-19:01
 */
data class UserRegisterVo(
    @field:NotBlank(message = "用户名不能为空")
    @field:Length(min = 6, max = 18, message = "用户名必须为6-18位字符")
    var username: String? = null,

    @field:NotBlank(message = "密码不能为空")
    @field:Length(min = 6, max = 18, message = "密码必须为6-18位字符")
    var password: String? = null,

    @field:NotBlank(message = "邮箱不能为空")
    @field:Email
    var email: String? = null,

    @field:NotNull(message = "验证码不能为空")
    var code: Int? = null
)
