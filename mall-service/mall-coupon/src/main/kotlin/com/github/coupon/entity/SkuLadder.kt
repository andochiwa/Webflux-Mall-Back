package com.github.coupon.entity

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
 * @date 2021-09-24 23:39:59
 */
@Table("sms_sku_ladder")
@ApiModel
data class SkuLadder (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var id: Long? = null,

	@ApiModelProperty(value = "spu_id")
	var skuId: Long? = null,

	@ApiModelProperty(value = "满几件")
	var fullCount: Int? = null,

	@ApiModelProperty(value = "打几折")
	var discount: BigDecimal? = null,

	@ApiModelProperty(value = "折后价")
	var price: BigDecimal? = null,

	@ApiModelProperty(value = "是否叠加其他优惠[0-不可叠加，1-可叠加]")
	var addOther: Int? = null,


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
