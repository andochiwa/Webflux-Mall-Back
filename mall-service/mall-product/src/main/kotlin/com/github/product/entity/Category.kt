package com.github.product.entity

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
 * @author andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-23 23:23:54
 */
@Table("pms_category")
@ApiModel
data class Category (

	@ApiModelProperty(value = "分类id")
	@Id
	@get:JvmName("deprecate")
	var catId: Long? = null,
	@ApiModelProperty(value = "分类名称")
	var name: String? = null,
	@ApiModelProperty(value = "父分类id")
	var parentCid: Long? = null,
	@ApiModelProperty(value = "层级")
	var catLevel: Int? = null,
	@ApiModelProperty(value = "是否显示[0-不显示，1显示]")
	var showStatus: Int? = null,
	@ApiModelProperty(value = "排序")
	var sort: Int? = null,
	@ApiModelProperty(value = "图标地址")
	var icon: String? = null,
	@ApiModelProperty(value = "计量单位")
	var productUnit: String? = null,
	@ApiModelProperty(value = "商品数量")
	var productCount: Int? = null,

): Serializable, Persistable<Long> {
	@JsonIgnore
	override fun isNew(): Boolean {
		return if (catId == null) {
			catId = IdUtil.getSnowflake().nextId()
			true
		} else false
	}
	override fun getId(): Long? = catId
}
