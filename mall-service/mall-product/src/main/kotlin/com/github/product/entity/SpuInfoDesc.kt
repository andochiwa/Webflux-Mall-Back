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
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Table("pms_spu_info_desc")
@ApiModel
data class SpuInfoDesc (

	@ApiModelProperty(value = "商品id")
	@Id
	@get:JvmName("deprecate")
	var spuId: Long? = null,

	@ApiModelProperty(value = "商品介绍")
	var description: String? = null,


) : Serializable, Persistable<Long> {
	@JsonIgnore
	override fun isNew(): Boolean {
		return if (spuId == null) {
			spuId = IdUtil.getSnowflake().nextId()
			true
		} else false
	}
	override fun getId(): Long? = spuId
}
