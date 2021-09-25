package com.github.member.entity

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
 * @date 2021-09-26 03:43:36
 */
@Table("ums_member_receive_address")
@ApiModel
data class MemberReceiveAddress (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "member_id")
	var memberId: Long? = null,

	@ApiModelProperty(value = "收货人姓名")
	var name: String? = null,

	@ApiModelProperty(value = "电话")
	var phone: String? = null,

	@ApiModelProperty(value = "邮政编码")
	var postCode: String? = null,

	@ApiModelProperty(value = "省份/直辖市")
	var province: String? = null,

	@ApiModelProperty(value = "城市")
	var city: String? = null,

	@ApiModelProperty(value = "区")
	var region: String? = null,

	@ApiModelProperty(value = "详细地址(街道)")
	var detailAddress: String? = null,

	@ApiModelProperty(value = "省市区代码")
	var areaCode: String? = null,

	@ApiModelProperty(value = "是否默认")
	var defaultStatus: Int? = null,


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
