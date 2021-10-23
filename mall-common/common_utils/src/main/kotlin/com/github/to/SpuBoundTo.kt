package com.github.to

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.math.BigDecimal

data class SpuBoundTo(
    @JsonSerialize(using = ToStringSerializer::class)
    var spuId: Long? = null,

    var growBounds: BigDecimal? = null,

    var buyBounds: BigDecimal? = null,

    var work: Int? = null,
)
