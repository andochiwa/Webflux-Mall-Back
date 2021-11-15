package com.github.search.dto

import com.github.search.entity.SkuEntity

data class SearchDto(
    var products: List<SkuEntity>? = null,

    var pageNum: Int,

    var totalNum: Long,

    var totalPage: Long,

    var brands: List<BrandVo>,

    var attrs: List<AttrVo>,

    var catelogs: List<CatelogVo>
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
