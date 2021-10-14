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
import java.time.LocalDateTime

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 23:39:59
 */
@Table("sms_coupon_history")
@ApiModel
data class CouponHistory (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var id: Long? = null,

	@ApiModelProperty(value = "优惠券id")
	@JsonSerialize(using = ToStringSerializer::class)
	var couponId: Long? = null,

	@ApiModelProperty(value = "会员id")
	@JsonSerialize(using = ToStringSerializer::class)
	var memberId: Long? = null,

	@ApiModelProperty(value = "会员名字")
	var memberNickName: String? = null,

	@ApiModelProperty(value = "获取方式[0->后台赠送；1->主动领取]")
	var getType: Int? = null,

	@ApiModelProperty(value = "创建时间")
	var createTime: LocalDateTime? = null,

	@ApiModelProperty(value = "使用状态[0->未使用；1->已使用；2->已过期]")
	var useType: Int? = null,

	@ApiModelProperty(value = "使用时间")
	var useTime: LocalDateTime? = null,

	@ApiModelProperty(value = "订单id")
	@JsonSerialize(using = ToStringSerializer::class)
	var orderId: Long? = null,

	@ApiModelProperty(value = "订单号")
	@JsonSerialize(using = ToStringSerializer::class)
	var orderSn: Long? = null,


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
