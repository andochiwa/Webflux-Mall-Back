package com.github.product.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
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
	var skuId: Long? = null,

	@ApiModelProperty(value = "spuId")
	var spuId: Long? = null,

	@ApiModelProperty(value = "sku名称")
	var skuName: String? = null,

	@ApiModelProperty(value = "sku介绍描述")
	var skuDesc: String? = null,

	@ApiModelProperty(value = "所属分类id")
	var catalogId: Long? = null,

	@ApiModelProperty(value = "品牌id")
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
	var saleCount: Long? = null,


) : Serializable, Persistable<Long> {
	@JsonIgnore
	override fun isNew(): Boolean {
		return if (skuId == null) {
			skuId = IdUtil.getSnowflake().nextId()
			true
		} else false
	}
	override fun getId(): Long? = skuId
}
