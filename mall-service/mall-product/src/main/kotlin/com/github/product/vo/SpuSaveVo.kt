package com.github.product.vo

import java.math.BigDecimal

data class SpuSaveVo(
    var baseAttrs: List<BaseAttrs>? = null,
    var bounds: Bounds? = null,
    var brandId: Long? = null,
    var catelogId: Long? = null,
    var description: List<String>? = null,
    var images: List<String>? = null,
    var publishStatus: Int? = null,
    var skus: List<Skus>? = null,
    var spuDescription: String? = null,
    var spuName: String? = null,
    var weight: BigDecimal? = null
) {
    data class BaseAttrs(
        var attrId: Long? = null,
        var attrValues: String? = null,
        var showDesc: Int? = null
    )

    data class Bounds(
        var buyBounds: BigDecimal? = null,
        var growBounds: BigDecimal? = null
    )

    data class Skus(
        var attr: List<Attr>? = null,
        var countStatus: Int? = null,
        var descar: List<String>? = null,
        var discount: BigDecimal? = null,
        var fullCount: Int? = null,
        var fullPrice: BigDecimal? = null,
        var images: List<Image>? = null,
        var memberPrice: List<MemberPrice>? = null,
        var price: BigDecimal? = null,
        var priceStatus: Int? = null,
        var reducePrice: BigDecimal? = null,
        var skuName: String? = null,
        var skuSubtitle: String? = null,
        var skuTitle: String? = null
    ) {
        data class Attr(
            var attrId: Long? = null,
            var attrName: String? = null,
            var attrValue: String? = null
        )

        data class Image(
            var defaultImg: Int? = null,
            var imgUrl: String? = null
        )

        data class MemberPrice(
            var id: Long? = null,
            var name: String? = null,
            var price: BigDecimal? = null
        )
    }
}
