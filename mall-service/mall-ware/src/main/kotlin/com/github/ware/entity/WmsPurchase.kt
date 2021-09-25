package com.github.ware.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:23
 */
@Table("wms_purchase")
@ApiModel
data class WmsPurchase (

	@ApiModelProperty(value = "")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "")
	var assigneeId: Long? = null,

	@ApiModelProperty(value = "")
	var assigneeName: String? = null,

	@ApiModelProperty(value = "")
	var phone: String? = null,

	@ApiModelProperty(value = "")
	var priority: Int? = null,

	@ApiModelProperty(value = "")
	var status: Int? = null,

	@ApiModelProperty(value = "")
	var wareId: Long? = null,

	@ApiModelProperty(value = "")
	var amount: BigDecimal? = null,

	@ApiModelProperty(value = "")
	var createTime: LocalDateTime? = null,

	@ApiModelProperty(value = "")
	var updateTime: LocalDateTime? = null,


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
