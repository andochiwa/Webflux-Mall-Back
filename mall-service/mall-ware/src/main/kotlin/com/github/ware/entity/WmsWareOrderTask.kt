package com.github.ware.entity

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
 * @date 2021-09-26 03:53:23
 */
@Table("wms_ware_order_task")
@ApiModel
data class WmsWareOrderTask (

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

	@ApiModelProperty(value = "收货人")
	var consignee: String? = null,

	@ApiModelProperty(value = "收货人电话")
	var consigneeTel: String? = null,

	@ApiModelProperty(value = "配送地址")
	var deliveryAddress: String? = null,

	@ApiModelProperty(value = "订单备注")
	var orderComment: String? = null,

	@ApiModelProperty(value = "付款方式【 1:在线付款 2:货到付款】")
	var paymentWay: Int? = null,

	@ApiModelProperty(value = "任务状态")
	var taskStatus: Int? = null,

	@ApiModelProperty(value = "订单描述")
	var orderBody: String? = null,

	@ApiModelProperty(value = "物流单号")
	var trackingNo: String? = null,

	@ApiModelProperty(value = "create_time")
	var createTime: LocalDateTime? = null,

	@ApiModelProperty(value = "仓库id")
	@JsonSerialize(using = ToStringSerializer::class)
	var wareId: Long? = null,

	@ApiModelProperty(value = "工作单备注")
	var taskComment: String? = null,


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
