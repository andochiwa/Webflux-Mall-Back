package com.github.member.vo

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class MemberLoginVo(
    @field:NotBlank
    var account: String? = null,

    @field:NotBlank
    @field:Length(min = 6, max = 18)
    var password: String? = null
)
