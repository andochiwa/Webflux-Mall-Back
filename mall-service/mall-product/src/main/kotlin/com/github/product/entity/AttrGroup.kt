package com.github.product.entity

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

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Table("pms_attr_group")
@ApiModel
data class AttrGroup (

	@ApiModelProperty(value = "分组id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var attrGroupId: Long? = null,

	@ApiModelProperty(value = "组名")
	var attrGroupName: String? = null,

	@ApiModelProperty(value = "排序")
	var sort: Int? = null,

	@ApiModelProperty(value = "描述")
	var description: String? = null,

	@ApiModelProperty(value = "组图标")
	var icon: String? = null,

	@ApiModelProperty(value = "所属分类id")
	@JsonSerialize(using = ToStringSerializer::class)
	var catelogId: Long? = null,


) : Serializable, Persistable<Long> {
	@JsonIgnore
	override fun isNew(): Boolean {
		return if (attrGroupId == null) {
			attrGroupId = IdUtil.getSnowflake().nextId()
			true
		} else false
	}
	@JsonSerialize(using = ToStringSerializer::class)
	override fun getId(): Long? = attrGroupId
}
