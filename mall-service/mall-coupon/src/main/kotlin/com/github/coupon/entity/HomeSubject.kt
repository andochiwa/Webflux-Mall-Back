package com.github.coupon.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 23:39:59
 */
@Table("sms_home_subject")
@ApiModel
data class HomeSubject (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "专题名字")
	var name: String? = null,

	@ApiModelProperty(value = "专题标题")
	var title: String? = null,

	@ApiModelProperty(value = "专题副标题")
	var subTitle: String? = null,

	@ApiModelProperty(value = "显示状态")
	var status: Int? = null,

	@ApiModelProperty(value = "详情连接")
	var url: String? = null,

	@ApiModelProperty(value = "排序")
	var sort: Int? = null,

	@ApiModelProperty(value = "专题图片地址")
	var img: String? = null,


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
