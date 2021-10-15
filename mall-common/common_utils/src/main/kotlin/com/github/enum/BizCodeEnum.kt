package com.github.enum

/**
 * @author Andochiwa
 * @version 1.0
 * @since 10-16-01:41
 */
enum class BizCodeEnum(val code: Int, val msg: String) {
    UNKNOWN_EXCEPTION(400, "未知异常"),

    VALID_EXCEPTION(401, "参数校验失败");
}
