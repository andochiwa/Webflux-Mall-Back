package com.github.to

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.math.BigDecimal

data class SkuInfoTo(
    @JsonSerialize(using = ToStringSerializer::class)
    var id: Long? = null,

    @JsonSerialize(using = ToStringSerializer::class)
    var spuId: Long? = null,
    var skuName: String? = null,
    var skuDesc: String? = null,
    @JsonSerialize(using = ToStringSerializer::class)
    var catelogId: Long? = null,
    @JsonSerialize(using = ToStringSerializer::class)
    var brandId: Long? = null,
    var skuDefaultImg: String? = null,
    var skuTitle: String? = null,
    var skuSubtitle: String? = null,
    var price: BigDecimal? = null,
    @JsonSerialize(using = ToStringSerializer::class)
    var saleCount: Long? = null,
)
