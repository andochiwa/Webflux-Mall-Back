package com.github.product.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.github.product.entity.AttrGroup

data class AttrGroupCategoryDto(
    @JsonSerialize(using = ToStringSerializer::class)
    var catId: Long? = null,

    var name: String? = null,

    @JsonSerialize(using = ToStringSerializer::class)
    var parentCid: Long? = null,

    var catLevel: Int? = null,

    var showStatus: Int? = null,

    var sort: Int = 0,

    var icon: String? = null,

    var productUnit: String? = null,

    var productCount: Int? = null,

    var attrGroupList: List<AttrGroup>? = null,
)
