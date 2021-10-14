package com.github.product.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
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
 * @date 2021-09-24 00:47:19
 */
@Table("pms_brand")
@ApiModel
data class Brand (

	@ApiModelProperty(value = "品牌id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	@JsonProperty("id")
	var brandId: Long? = null,

	@ApiModelProperty(value = "品牌名")
	var name: String? = null,

	@ApiModelProperty(value = "品牌logo地址")
	var logo: String? = null,

	@ApiModelProperty(value = "介绍")
	var description: String? = null,

	@ApiModelProperty(value = "显示状态[0-不显示；1-显示]")
	var showStatus: Int? = null,

	@ApiModelProperty(value = "检索首字母")
	var firstLetter: String? = null,

	@ApiModelProperty(value = "排序")
	var sort: Int? = null,


) : Serializable, Persistable<Long> {
	@JsonIgnore
	override fun isNew(): Boolean {
		return if (brandId == null) {
			brandId = IdUtil.getSnowflake().nextId()
			true
		} else false
	}
	@JsonSerialize(using = ToStringSerializer::class)
	override fun getId(): Long? = brandId
}
