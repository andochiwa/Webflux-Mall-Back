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
 * @date 2021-09-24 23:39:59
 */
@Table("sms_home_adv")
@ApiModel
data class HomeAdv (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var id: Long? = null,

	@ApiModelProperty(value = "名字")
	var name: String? = null,

	@ApiModelProperty(value = "图片地址")
	var pic: String? = null,

	@ApiModelProperty(value = "开始时间")
	var startTime: LocalDateTime? = null,

	@ApiModelProperty(value = "结束时间")
	var endTime: LocalDateTime? = null,

	@ApiModelProperty(value = "状态")
	var status: Int? = null,

	@ApiModelProperty(value = "点击数")
	var clickCount: Int? = null,

	@ApiModelProperty(value = "广告详情连接地址")
	var url: String? = null,

	@ApiModelProperty(value = "备注")
	var note: String? = null,

	@ApiModelProperty(value = "排序")
	var sort: Int? = null,

	@ApiModelProperty(value = "发布者")
	@JsonSerialize(using = ToStringSerializer::class)
	var publisherId: Long? = null,

	@ApiModelProperty(value = "审核者")
	@JsonSerialize(using = ToStringSerializer::class)
	var authId: Long? = null,


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
