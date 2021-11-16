package com.github.search.vo

data class SearchParamVo(
    var keyword: String? = null,
    var catelog3Id: Int? = null,
    var sort: String? = null, // sort=saleCount_asc
    var hasStock: Int? = null,
    var skuPrice: String? = null, // skuPrice=1_500
    var brandId: List<Long>? = null,
    var attrs: List<String>? = null, // attrs=2_白色:黑色
    var pageNum: Int? = null
)
