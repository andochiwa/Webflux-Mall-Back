package com.github.member.entity

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
 * @date 2021-09-26 03:43:36
 */
@Table("ums_growth_change_history")
@ApiModel
data class GrowthChangeHistory (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var id: Long? = null,

	@ApiModelProperty(value = "member_id")
	@JsonSerialize(using = ToStringSerializer::class)
	var memberId: Long? = null,

	@ApiModelProperty(value = "create_time")
	var createTime: LocalDateTime? = null,

	@ApiModelProperty(value = "改变的值（正负计数）")
	var changeCount: Int? = null,

	@ApiModelProperty(value = "备注")
	var note: String? = null,

	@ApiModelProperty(value = "积分来源[0-购物，1-管理员修改]")
	var sourceType: Int? = null,


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
