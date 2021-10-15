package com.github.product.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.github.vaild.AddGroup
import com.github.vaild.UpdateGroup
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Range
import org.hibernate.validator.constraints.URL
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import javax.validation.constraints.*

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
	@field:NotNull(groups = [UpdateGroup::class])
	@field:Null(groups = [AddGroup::class])
	var brandId: Long? = null,

	@ApiModelProperty(value = "品牌名")
	@field:NotBlank(groups = [AddGroup::class, UpdateGroup::class])
	var name: String? = null,

	@ApiModelProperty(value = "品牌logo地址")
	@field:URL(groups = [AddGroup::class, UpdateGroup::class])
	var logo: String? = null,

	@ApiModelProperty(value = "介绍")
	var description: String? = null,

	@ApiModelProperty(value = "显示状态[0-不显示；1-显示]")
	@field:Range(min = 0, max = 1, groups = [AddGroup::class, UpdateGroup::class])
	var showStatus: Int? = null,

	@ApiModelProperty(value = "检索首字母")
	@field:Pattern(regexp = "^[a-zA-Z]$", groups = [AddGroup::class, UpdateGroup::class])
	var firstLetter: String? = null,

	@ApiModelProperty(value = "排序")
	@field:Min(value = 0, groups = [AddGroup::class, UpdateGroup::class])
	@field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
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
