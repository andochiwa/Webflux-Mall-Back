package com.github.product.vo

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer

data class Catelog2Vo(
    @JsonSerialize(using = ToStringSerializer::class)
    var id: Long? = null,

    var name: String? = null,

    @JsonSerialize(using = ToStringSerializer::class)
    var catelog1Id: Long? = null,

    var catelog3List: List<Catelog3Vo>? = null,

    ) {
    data class Catelog3Vo(
        @JsonSerialize(using = ToStringSerializer::class)
        var id: Long? = null,

        var name: String? = null,

        @JsonSerialize(using = ToStringSerializer::class)
        var catelog2Id: Long? = null,
    )
}
