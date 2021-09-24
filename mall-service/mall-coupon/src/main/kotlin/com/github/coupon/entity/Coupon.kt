package com.github.coupon.entity

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
 * @date 2021-09-24 23:39:59
 */
@Table("sms_coupon")
@ApiModel
data class Coupon (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
	var couponType: Int? = null,

	@ApiModelProperty(value = "优惠券图片")
	var couponImg: String? = null,

	@ApiModelProperty(value = "优惠卷名字")
	var couponName: String? = null,

	@ApiModelProperty(value = "数量")
	var num: Int? = null,

	@ApiModelProperty(value = "金额")
	var amount: BigDecimal? = null,

	@ApiModelProperty(value = "每人限领张数")
	var perLimit: Int? = null,

	@ApiModelProperty(value = "使用门槛")
	var minPoint: BigDecimal? = null,

	@ApiModelProperty(value = "开始时间")
	var startTime: LocalDateTime? = null,

	@ApiModelProperty(value = "结束时间")
	var endTime: LocalDateTime? = null,

	@ApiModelProperty(value = "使用类型[0->全场通用；1->指定分类；2->指定商品]")
	var useType: Int? = null,

	@ApiModelProperty(value = "备注")
	var note: String? = null,

	@ApiModelProperty(value = "发行数量")
	var publishCount: Int? = null,

	@ApiModelProperty(value = "已使用数量")
	var useCount: Int? = null,

	@ApiModelProperty(value = "领取数量")
	var receiveCount: Int? = null,

	@ApiModelProperty(value = "可以领取的开始日期")
	var enableStartTime: LocalDateTime? = null,

	@ApiModelProperty(value = "可以领取的结束日期")
	var enableEndTime: LocalDateTime? = null,

	@ApiModelProperty(value = "优惠码")
	var code: String? = null,

	@ApiModelProperty(value = "可以领取的会员等级[0->不限等级，其他-对应等级]")
	var memberLevel: Int? = null,

	@ApiModelProperty(value = "发布状态[0-未发布，1-已发布]")
	var publish: Int? = null,


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
