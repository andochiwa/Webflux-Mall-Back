package com.github.member.entity

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
 * @date 2021-09-26 03:43:36
 */
@Table("ums_member_login_log")
@ApiModel
data class MemberLoginLog (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "member_id")
	var memberId: Long? = null,

	@ApiModelProperty(value = "创建时间")
	var createTime: LocalDateTime? = null,

	@ApiModelProperty(value = "ip")
	var ip: String? = null,

	@ApiModelProperty(value = "city")
	var city: String? = null,

	@ApiModelProperty(value = "登录类型[1-web，2-app]")
	var loginType: Int? = null,


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
