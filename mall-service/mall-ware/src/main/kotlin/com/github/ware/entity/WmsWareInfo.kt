package com.github.ware.entity

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
 * @date 2021-09-26 03:53:24
 */
@Table("wms_ware_info")
@ApiModel
data class WmsWareInfo (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "仓库名")
	var name: String? = null,

	@ApiModelProperty(value = "仓库地址")
	var address: String? = null,

	@ApiModelProperty(value = "区域编码")
	var areaCode: String? = null,


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
