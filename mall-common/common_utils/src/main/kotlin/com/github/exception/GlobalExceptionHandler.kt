package com.github.exception

import com.github.dto.ResultDto
import com.github.dto.resultError
import com.github.enum.BizCodeEnum
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException

/**
 * @author Andochiwa
 * @version 1.0
 * @since 09-27-04:26
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException::class)
    suspend fun validateException(e: WebExchangeBindException): ResultDto {
        e.printStackTrace()
        val map = mutableMapOf<String, String>()
        e.bindingResult.fieldErrors.forEach {
            map[it.field] = it.defaultMessage ?: "unknown error"
        }
        return resultError(BizCodeEnum.VALID_EXCEPTION.code, BizCodeEnum.VALID_EXCEPTION.msg)
            .putAll(map)
    }

    @ExceptionHandler(Exception::class)
    suspend fun globalException(e: Exception): ResultDto {
        e.printStackTrace()
        return resultError(BizCodeEnum.UNKNOWN_EXCEPTION.code, e.message ?: BizCodeEnum.UNKNOWN_EXCEPTION.msg)
    }
}
