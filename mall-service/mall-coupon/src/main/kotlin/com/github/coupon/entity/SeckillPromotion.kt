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
 * @date 2021-09-27 04:41:21
 */
@Table("sms_seckill_promotion")
@ApiModel
data class SeckillPromotion (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var id: Long? = null,

	@ApiModelProperty(value = "活动标题")
	var title: String? = null,

	@ApiModelProperty(value = "开始日期")
	var startTime: LocalDateTime? = null,

	@ApiModelProperty(value = "结束日期")
	var endTime: LocalDateTime? = null,

	@ApiModelProperty(value = "上下线状态")
	var status: Int? = null,

	@ApiModelProperty(value = "创建时间")
	var createTime: LocalDateTime? = null,

	@ApiModelProperty(value = "创建人")
	@JsonSerialize(using = ToStringSerializer::class)
	var userId: Long? = null,


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
