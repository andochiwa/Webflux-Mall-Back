package com.github.product.dto

import com.github.product.entity.SkuImages
import com.github.product.entity.SkuInfo
import com.github.product.entity.SpuInfoDesc

data class SkuItemDto(
    var skuInfo: SkuInfo? = null,

    var skuImages: List<SkuImages>? = null,

    var spuInfoDesc: SpuInfoDesc? = null,

    var saleAttrs: List<SkuItemSaleAttrDto>? = null,

    var groupAttrs: List<SpuItemAttrGroupDto>? = null,
) {
    data class SkuItemSaleAttrDto(
        var attrId: Long? = null,

        var attrName: String? = null,

        var attrValues: List<String>? = null,
    )

    data class SpuItemAttrGroupDto(
        var groupName: String? = null,

        var attrs: List<SpuAttrGroupDto>? = null,
    )

    data class SpuAttrGroupDto(
        var attrName: String? = null,

        var attrValue: String? = null,
    )
}
