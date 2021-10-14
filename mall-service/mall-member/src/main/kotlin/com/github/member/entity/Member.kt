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
import java.time.LocalDate
import java.time.LocalDateTime

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:43:36
 */
@Table("ums_member")
@ApiModel
data class Member (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var id: Long? = null,

	@ApiModelProperty(value = "会员等级id")
	@JsonSerialize(using = ToStringSerializer::class)
	var levelId: Long? = null,

	@ApiModelProperty(value = "用户名")
	var username: String? = null,

	@ApiModelProperty(value = "密码")
	var password: String? = null,

	@ApiModelProperty(value = "昵称")
	var nickname: String? = null,

	@ApiModelProperty(value = "手机号码")
	var mobile: String? = null,

	@ApiModelProperty(value = "邮箱")
	var email: String? = null,

	@ApiModelProperty(value = "头像")
	var header: String? = null,

	@ApiModelProperty(value = "性别")
	var gender: Int? = null,

	@ApiModelProperty(value = "生日")
	var birth: LocalDate? = null,

	@ApiModelProperty(value = "所在城市")
	var city: String? = null,

	@ApiModelProperty(value = "职业")
	var job: String? = null,

	@ApiModelProperty(value = "个性签名")
	var sign: String? = null,

	@ApiModelProperty(value = "用户来源")
	var sourceType: Int? = null,

	@ApiModelProperty(value = "积分")
	var integration: Int? = null,

	@ApiModelProperty(value = "成长值")
	var growth: Int? = null,

	@ApiModelProperty(value = "启用状态")
	var status: Int? = null,

	@ApiModelProperty(value = "注册时间")
	var createTime: LocalDateTime? = null,


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
