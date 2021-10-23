package com.github.product.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.github.product.entity.Attr

data class AttrGroupWithAttrsDto(
    @JsonSerialize(using = ToStringSerializer::class)
    @JsonProperty("attrGroupId")
    var id: Long? = null,

    var attrGroupName: String? = null,

    var sort: Int? = null,

    var description: String? = null,

    var icon: String? = null,

    @JsonSerialize(using = ToStringSerializer::class)
    var catelogId: Long? = null,

    var attrList: List<Attr>? = null,
)
