package com.github.dto

/**
 * @author Andochiwa
 * @version 1.0
 * @since 09-24-00:12
 */
class ResultDto(code: Int = 200) {

    private val data = mutableMapOf<String, Any?>()

    init {
        data["code"] = code
    }

    fun put(key: String, value: Any?): ResultDto {
        data[key] = value
        return this
    }

    fun putAll(map: Map<String, Any>): ResultDto {
        data.putAll(map)
        return this
    }

}

fun resultSuccess(msg: String = "success"): ResultDto {
    val dto = ResultDto()
    dto.put("msg", msg)
    return dto
}

fun resultSuccess(data: Map<String, Any>): ResultDto {
    val dto = ResultDto()
    dto.putAll(data)
    return dto
}

fun resultError(code: Int = 404, msg: String = "error"): ResultDto {
    val dto = ResultDto(code)
    dto.put("msg", msg)
    return dto
}
