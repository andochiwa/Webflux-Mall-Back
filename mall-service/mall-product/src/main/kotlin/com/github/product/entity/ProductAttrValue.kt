package com.github.product.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:43:25
 */
@Table("pms_product_attr_value")
@ApiModel
data class ProductAttrValue (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var id: Long? = null,

	@ApiModelProperty(value = "商品id")
	@JsonSerialize(using = ToStringSerializer::class)
	var spuId: Long? = null,

	@ApiModelProperty(value = "属性id")
	@JsonSerialize(using = ToStringSerializer::class)
	var attrId: Long? = null,

	@ApiModelProperty(value = "属性名")
	var attrName: String? = null,

	@ApiModelProperty(value = "属性值")
	var attrValue: String? = null,

	@ApiModelProperty(value = "顺序")
	var attrSort: Int? = null,

	@ApiModelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】")
	var quickShow: Int? = null,


) : Serializable, Persistable<Long> {
	@JsonIgnore
	override fun isNew(): Boolean {
		return if (id == null) {
			id = IdUtil.getSnowflake().nextId()
			true
		} else false
	}
	@JsonSerialize(using = ToStringSerializer::class)
	override fun getId(): Long? = id
}
