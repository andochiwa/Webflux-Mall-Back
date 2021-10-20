package com.github.product.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AttrDto(
    @JsonSerialize(using = ToStringSerializer::class)
    var id: Long? = null,

    var attrName: String? = null,

    var searchType: Int? = null,

    var icon: String? = null,

    var valueSelect: String? = null,

    var attrType: Int? = null,

    @JsonSerialize(using = ToStringSerializer::class)
    var enable: Long? = null,

    @JsonSerialize(using = ToStringSerializer::class)
    var catelogId: Long? = null,

    var showDesc: Int? = null,

    var catelogName: String? = null,

    var groupName: String? = null,

    var catelogPath: MutableList<Long>? = null
) : Serializable
