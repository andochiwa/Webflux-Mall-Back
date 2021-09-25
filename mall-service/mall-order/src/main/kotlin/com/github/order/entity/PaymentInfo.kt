package com.github.order.entity

import java.math.BigDecimal
import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
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
 * @date 2021-09-26 03:18:13
 */
@Table("oms_payment_info")
@ApiModel
data class PaymentInfo (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "订单号（对外业务号）")
	var orderSn: String? = null,

	@ApiModelProperty(value = "订单id")
	var orderId: Long? = null,

	@ApiModelProperty(value = "支付宝交易流水号")
	var alipayTradeNo: String? = null,

	@ApiModelProperty(value = "支付总金额")
	var totalAmount: BigDecimal? = null,

	@ApiModelProperty(value = "交易内容")
	var subject: String? = null,

	@ApiModelProperty(value = "支付状态")
	var paymentStatus: String? = null,

	@ApiModelProperty(value = "创建时间")
	var createTime: LocalDateTime? = null,

	@ApiModelProperty(value = "确认时间")
	var confirmTime: LocalDateTime? = null,

	@ApiModelProperty(value = "回调内容")
	var callbackContent: String? = null,

	@ApiModelProperty(value = "回调时间")
	var callbackTime: LocalDateTime? = null,


	) : Serializable, Persistable<Long> {
	@JsonIgnore
	override fun isNew(): Boolean {
		return if (id == null) {
			id = IdUtil.getSnowflake().nextId()
			true
		} else false
	}
	override fun getId(): Long? = id
}
