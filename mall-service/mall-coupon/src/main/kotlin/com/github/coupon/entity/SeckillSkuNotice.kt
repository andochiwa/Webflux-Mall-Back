package com.github.coupon.entity

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
 * @date 2021-09-27 04:41:21
 */
@Table("sms_seckill_sku_notice")
@ApiModel
data class SeckillSkuNotice (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "member_id")
	var memberId: Long? = null,

	@ApiModelProperty(value = "sku_id")
	var skuId: Long? = null,

	@ApiModelProperty(value = "活动场次id")
	var sessionId: Long? = null,

	@ApiModelProperty(value = "订阅时间")
	var subscribeTime: LocalDateTime? = null,

	@ApiModelProperty(value = "发送时间")
	var sendTime: LocalDateTime? = null,

	@ApiModelProperty(value = "通知方式[0-短信，1-邮件]")
	var noticeType: Int? = null,


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
