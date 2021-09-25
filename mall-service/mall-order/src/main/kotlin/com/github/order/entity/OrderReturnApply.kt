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
@Table("oms_order_return_apply")
@ApiModel
data class OrderReturnApply (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "order_id")
	var orderId: Long? = null,

	@ApiModelProperty(value = "退货商品id")
	var skuId: Long? = null,

	@ApiModelProperty(value = "订单编号")
	var orderSn: String? = null,

	@ApiModelProperty(value = "申请时间")
	var createTime: LocalDateTime? = null,

	@ApiModelProperty(value = "会员用户名")
	var memberUsername: String? = null,

	@ApiModelProperty(value = "退款金额")
	var returnAmount: BigDecimal? = null,

	@ApiModelProperty(value = "退货人姓名")
	var returnName: String? = null,

	@ApiModelProperty(value = "退货人电话")
	var returnPhone: String? = null,

	@ApiModelProperty(value = "申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]")
	var status: Int? = null,

	@ApiModelProperty(value = "处理时间")
	var handleTime: LocalDateTime? = null,

	@ApiModelProperty(value = "商品图片")
	var skuImg: String? = null,

	@ApiModelProperty(value = "商品名称")
	var skuName: String? = null,

	@ApiModelProperty(value = "商品品牌")
	var skuBrand: String? = null,

	@ApiModelProperty(value = "商品销售属性(JSON)")
	var skuAttrsVals: String? = null,

	@ApiModelProperty(value = "退货数量")
	var skuCount: Int? = null,

	@ApiModelProperty(value = "商品单价")
	var skuPrice: BigDecimal? = null,

	@ApiModelProperty(value = "商品实际支付单价")
	var skuRealPrice: BigDecimal? = null,

	@ApiModelProperty(value = "原因")
	var reason: String? = null,

	@ApiModelProperty(value = "描述")
	var description: String? = null,

	@ApiModelProperty(value = "凭证图片，以逗号隔开")
	var descPics: String? = null,

	@ApiModelProperty(value = "处理备注")
	var handleNote: String? = null,

	@ApiModelProperty(value = "处理人员")
	var handleMan: String? = null,

	@ApiModelProperty(value = "收货人")
	var receiveMan: String? = null,

	@ApiModelProperty(value = "收货时间")
	var receiveTime: LocalDateTime? = null,

	@ApiModelProperty(value = "收货备注")
	var receiveNote: String? = null,

	@ApiModelProperty(value = "收货电话")
	var receivePhone: String? = null,

	@ApiModelProperty(value = "公司收货地址")
	var companyAddress: String? = null,


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
