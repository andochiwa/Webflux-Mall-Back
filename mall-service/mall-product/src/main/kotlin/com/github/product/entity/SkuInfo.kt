package com.github.product.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.math.BigDecimal

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:43:25
 */
@Table("pms_sku_info")
@ApiModel
data class SkuInfo (

	@ApiModelProperty(value = "skuId")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	@JsonProperty("id")
	var skuId: Long? = null,

	@ApiModelProperty(value = "spuId")
	@JsonSerialize(using = ToStringSerializer::class)
	var spuId: Long? = null,

	@ApiModelProperty(value = "sku名称")
	var skuName: String? = null,

	@ApiModelProperty(value = "sku介绍描述")
	var skuDesc: String? = null,

	@ApiModelProperty(value = "所属分类id")
	@JsonSerialize(using = ToStringSerializer::class)
	var catalogId: Long? = null,

	@ApiModelProperty(value = "品牌id")
	@JsonSerialize(using = ToStringSerializer::class)
	var brandId: Long? = null,

	@ApiModelProperty(value = "默认图片")
	var skuDefaultImg: String? = null,

	@ApiModelProperty(value = "标题")
	var skuTitle: String? = null,

	@ApiModelProperty(value = "副标题")
	var skuSubtitle: String? = null,

	@ApiModelProperty(value = "价格")
	var price: BigDecimal? = null,

	@ApiModelProperty(value = "销量")
	@JsonSerialize(using = ToStringSerializer::class)
	var saleCount: Long? = null,


) : Serializable, Persistable<Long> {
	@JsonIgnore
	override fun isNew(): Boolean {
		return if (skuId == null) {
			skuId = IdUtil.getSnowflake().nextId()
			true
		} else false
	}
	@JsonSerialize(using = ToStringSerializer::class)
	override fun getId(): Long? = skuId
}
