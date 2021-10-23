package com.github.to

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.math.BigDecimal

data class SkuFullReductionTo(
    @JsonSerialize(using = ToStringSerializer::class)
    var skuId: Long? = null,

    var discount: BigDecimal? = null,

    // addOrder
    var countStatus: Int? = null,

    var fullCount: Int? = null,

    var fullPrice: BigDecimal? = null,

    var reducePrice: BigDecimal? = null,

    var price: BigDecimal? = null,

    var priceStatus: Int? = null,

    var memberPrice: List<MemberPriceTo>? = null,
) {
    data class MemberPriceTo(
        var id: Long? = null,
        var name: String? = null,
        var price: BigDecimal? = null,
    )
}
