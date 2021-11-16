package com.github.search.dto

import com.github.search.entity.SkuEntity

data class SearchDto(
    var products: List<SkuEntity>? = null,

    var pageNum: Int? = null,

    var totalNum: Long? = null,

    var totalPage: Long? = null,

    var brands: List<BrandVo>? = null,

    var attrs: List<AttrVo>? = null,

    var catelogs: List<CatelogVo>? = null
) {
    data class BrandVo(
        var brandId: Long? = null,

        var brandName: String? = null,

        var brandImg: String? = null,
    )

    data class AttrVo(
        var attrId: Long? = null,

        var attrName: String? = null,

        var attrValue: List<String>? = null
    )

    data class CatelogVo(
        var catelogId: Long? = null,

        var catelogName: String? = null,
    )
}
