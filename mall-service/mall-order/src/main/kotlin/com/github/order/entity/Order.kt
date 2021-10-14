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
import java.time.LocalDateTime

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:18:13
 */
@Table("oms_order")
@ApiModel
data class Order (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var id: Long? = null,

	@ApiModelProperty(value = "member_id")
	@JsonSerialize(using = ToStringSerializer::class)
	var memberId: Long? = null,

	@ApiModelProperty(value = "订单号")
	var orderSn: String? = null,

	@ApiModelProperty(value = "使用的优惠券")
	@JsonSerialize(using = ToStringSerializer::class)
	var couponId: Long? = null,

	@ApiModelProperty(value = "create_time")
	var createTime: LocalDateTime? = null,

	@ApiModelProperty(value = "用户名")
	var memberUsername: String? = null,

	@ApiModelProperty(value = "订单总额")
	var totalAmount: BigDecimal? = null,

	@ApiModelProperty(value = "应付总额")
	var payAmount: BigDecimal? = null,

	@ApiModelProperty(value = "运费金额")
	var freightAmount: BigDecimal? = null,

	@ApiModelProperty(value = "促销优化金额（促销价、满减、阶梯价）")
	var promotionAmount: BigDecimal? = null,

	@ApiModelProperty(value = "积分抵扣金额")
	var integrationAmount: BigDecimal? = null,

	@ApiModelProperty(value = "优惠券抵扣金额")
	var couponAmount: BigDecimal? = null,

	@ApiModelProperty(value = "后台调整订单使用的折扣金额")
	var discountAmount: BigDecimal? = null,

	@ApiModelProperty(value = "支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】")
	var payType: Int? = null,

	@ApiModelProperty(value = "订单来源[0->PC订单；1->app订单]")
	var sourceType: Int? = null,

	@ApiModelProperty(value = "订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】")
	var status: Int? = null,

	@ApiModelProperty(value = "物流公司(配送方式)")
	var deliveryCompany: String? = null,

	@ApiModelProperty(value = "物流单号")
	var deliverySn: String? = null,

	@ApiModelProperty(value = "自动确认时间（天）")
	var autoConfirmDay: Int? = null,

	@ApiModelProperty(value = "可以获得的积分")
	var integration: Int? = null,

	@ApiModelProperty(value = "可以获得的成长值")
	var growth: Int? = null,

	@ApiModelProperty(value = "发票类型[0->不开发票；1->电子发票；2->纸质发票]")
	var billType: Int? = null,

	@ApiModelProperty(value = "发票抬头")
	var billHeader: String? = null,

	@ApiModelProperty(value = "发票内容")
	var billContent: String? = null,

	@ApiModelProperty(value = "收票人电话")
	var billReceiverPhone: String? = null,

	@ApiModelProperty(value = "收票人邮箱")
	var billReceiverEmail: String? = null,

	@ApiModelProperty(value = "收货人姓名")
	var receiverName: String? = null,

	@ApiModelProperty(value = "收货人电话")
	var receiverPhone: String? = null,

	@ApiModelProperty(value = "收货人邮编")
	var receiverPostCode: String? = null,

	@ApiModelProperty(value = "省份/直辖市")
	var receiverProvince: String? = null,

	@ApiModelProperty(value = "城市")
	var receiverCity: String? = null,

	@ApiModelProperty(value = "区")
	var receiverRegion: String? = null,

	@ApiModelProperty(value = "详细地址")
	var receiverDetailAddress: String? = null,

	@ApiModelProperty(value = "订单备注")
	var note: String? = null,

	@ApiModelProperty(value = "确认收货状态[0->未确认；1->已确认]")
	var confirmStatus: Int? = null,

	@ApiModelProperty(value = "删除状态【0->未删除；1->已删除】")
	var deleteStatus: Int? = null,

	@ApiModelProperty(value = "下单时使用的积分")
	var useIntegration: Int? = null,

	@ApiModelProperty(value = "支付时间")
	var paymentTime: LocalDateTime? = null,

	@ApiModelProperty(value = "发货时间")
	var deliveryTime: LocalDateTime? = null,

	@ApiModelProperty(value = "确认收货时间")
	var receiveTime: LocalDateTime? = null,

	@ApiModelProperty(value = "评价时间")
	var commentTime: LocalDateTime? = null,

	@ApiModelProperty(value = "修改时间")
	var modifyTime: LocalDateTime? = null,


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
