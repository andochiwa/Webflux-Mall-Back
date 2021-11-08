package com.github.search.entity

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.math.BigDecimal

@Document(indexName = "product")
data class SkuEntity(
    @Id
    @Field(type = FieldType.Long)
    @JsonSerialize(using = ToStringSerializer::class)
    var skuId: Long? = null,

    @Field(type = FieldType.Long)
    @JsonSerialize(using = ToStringSerializer::class)
    var spuId: Long? = null,

    @Field(type = FieldType.Text, analyzer = "ik_smart")
    var skuTitle: String? = null,

    @Field(type = FieldType.Scaled_Float)
    var skuPrice: BigDecimal? = null,

    @Field(type = FieldType.Keyword, index = false, docValues = false)
    var skuImg: String? = null,

    @Field(type = FieldType.Long)
    var saleCount: Long? = null,

    @Field(type = FieldType.Boolean)
    var hasStock: Boolean = false,

    @Field(type = FieldType.Long)
    var hotScore: Long = 0,

    @Field(type = FieldType.Long)
    @JsonSerialize(using = ToStringSerializer::class)
    var brandId: Long? = null,

    @Field(type = FieldType.Long)
    @JsonSerialize(using = ToStringSerializer::class)
    var catelogId: Long? = null,

    @Field(type = FieldType.Keyword, index = false, docValues = false)
    var brandName: String? = null,

    @Field(type = FieldType.Keyword, index = false, docValues = false)
    var brandImg: String? = null,

    @Field(type = FieldType.Keyword, index = false, docValues = false)
    var catelogName: String? = null,

    @Field(type = FieldType.Nested)
    var attrs: List<Attrs>? = null,
) : Persistable<Long> {
    data class Attrs(
        @Field(type = FieldType.Long)
        @JsonSerialize(using = ToStringSerializer::class)
        var attrId: Long? = null,

        @Field(type = FieldType.Keyword, index = false, docValues = false)
        var attrName: String? = null,

        @Field(type = FieldType.Keyword)
        var attrValue: String? = null,
    )

    @JsonSerialize(using = ToStringSerializer::class)
    override fun getId(): Long? = skuId

    override fun isNew(): Boolean = false
}
