package com.github.exception

import com.github.dto.ResultDto
import com.github.dto.resultError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author Andochiwa
 * @version 1.0
 * @since 09-27-04:26
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    suspend fun globalException(e: Exception): ResultDto {
        e.printStackTrace()
        return resultError(msg = e.message ?: "error")
    }
}
