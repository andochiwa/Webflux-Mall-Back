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
@Table("sms_seckill_session")
@ApiModel
data class SeckillSession (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "场次名称")
	var name: String? = null,

	@ApiModelProperty(value = "每日开始时间")
	var startTime: LocalDateTime? = null,

	@ApiModelProperty(value = "每日结束时间")
	var endTime: LocalDateTime? = null,

	@ApiModelProperty(value = "启用状态")
	var status: Int? = null,

	@ApiModelProperty(value = "创建时间")
	var createTime: LocalDateTime? = null,


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
