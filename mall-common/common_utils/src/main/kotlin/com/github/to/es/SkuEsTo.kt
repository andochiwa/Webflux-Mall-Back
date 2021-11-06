package com.github.to.es

import java.math.BigDecimal


data class SkuEsTo(
    var skuId: Long? = null,
    var spuId: Long? = null,
    var skuTitle: String? = null,
    var skuPrice: BigDecimal? = null,
    var skuImg: String? = null,
    var saleCount: Long? = null,
    var hasStock: Boolean = false,
    var hotScore: Long = 0,
    var brandId: Long? = null,
    var catelogId: Long? = null,
    var brandName: String? = null,
    var brandImg: String? = null,
    var catelogName: String? = null,
    var attrs: List<Attrs>? = null
) {
    data class Attrs(
        var attrId: Long? = null,
        var attrName: String? = null,
        var attrValue: String? = null
    )
}
