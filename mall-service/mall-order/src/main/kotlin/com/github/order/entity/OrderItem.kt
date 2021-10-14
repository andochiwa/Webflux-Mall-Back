package com.github.order.entity

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
import java.math.BigDecimal

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:18:13
 */
@Table("oms_order_item")
@ApiModel
data class OrderItem (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var id: Long? = null,

	@ApiModelProperty(value = "order_id")
	@JsonSerialize(using = ToStringSerializer::class)
	var orderId: Long? = null,

	@ApiModelProperty(value = "order_sn")
	var orderSn: String? = null,

	@ApiModelProperty(value = "spu_id")
	@JsonSerialize(using = ToStringSerializer::class)
	var spuId: Long? = null,

	@ApiModelProperty(value = "spu_name")
	var spuName: String? = null,

	@ApiModelProperty(value = "spu_pic")
	var spuPic: String? = null,

	@ApiModelProperty(value = "品牌")
	var spuBrand: String? = null,

	@ApiModelProperty(value = "商品分类id")
	@JsonSerialize(using = ToStringSerializer::class)
	var categoryId: Long? = null,

	@ApiModelProperty(value = "商品sku编号")
	@JsonSerialize(using = ToStringSerializer::class)
	var skuId: Long? = null,

	@ApiModelProperty(value = "商品sku名字")
	var skuName: String? = null,

	@ApiModelProperty(value = "商品sku图片")
	var skuPic: String? = null,

	@ApiModelProperty(value = "商品sku价格")
	var skuPrice: BigDecimal? = null,

	@ApiModelProperty(value = "商品购买的数量")
	var skuQuantity: Int? = null,

	@ApiModelProperty(value = "商品销售属性组合（JSON）")
	var skuAttrsVals: String? = null,

	@ApiModelProperty(value = "商品促销分解金额")
	var promotionAmount: BigDecimal? = null,

	@ApiModelProperty(value = "优惠券优惠分解金额")
	var couponAmount: BigDecimal? = null,

	@ApiModelProperty(value = "积分优惠分解金额")
	var integrationAmount: BigDecimal? = null,

	@ApiModelProperty(value = "该商品经过优惠后的分解金额")
	var realAmount: BigDecimal? = null,

	@ApiModelProperty(value = "赠送积分")
	var giftIntegration: Int? = null,

	@ApiModelProperty(value = "赠送成长值")
	var giftGrowth: Int? = null,


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
