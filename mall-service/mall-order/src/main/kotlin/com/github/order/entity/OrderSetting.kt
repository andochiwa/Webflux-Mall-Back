package com.github.order.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
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
 * @date 2021-09-26 03:18:13
 */
@Table("oms_order_setting")
@ApiModel
data class OrderSetting (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "秒杀订单超时关闭时间(分)")
	var flashOrderOvertime: Int? = null,

	@ApiModelProperty(value = "正常订单超时时间(分)")
	var normalOrderOvertime: Int? = null,

	@ApiModelProperty(value = "发货后自动确认收货时间（天）")
	var confirmOvertime: Int? = null,

	@ApiModelProperty(value = "自动完成交易时间，不能申请退货（天）")
	var finishOvertime: Int? = null,

	@ApiModelProperty(value = "订单完成后自动好评时间（天）")
	var commentOvertime: Int? = null,

	@ApiModelProperty(value = "会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】")
	var memberLevel: Int? = null,


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
