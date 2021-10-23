package com.github.product.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer

data class BrandDto(
    @JsonSerialize(using = ToStringSerializer::class)
    var brandId: Long?,
    @JsonSerialize(using = ToStringSerializer::class)
    var brandName: String?
)
