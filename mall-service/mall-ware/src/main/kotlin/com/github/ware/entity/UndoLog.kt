package com.github.ware.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
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
 * @date 2021-09-26 03:53:23
 */
@Table("undo_log")
@ApiModel
data class UndoLog (

	@ApiModelProperty(value = "")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "")
	var branchId: Long? = null,

	@ApiModelProperty(value = "")
	var xid: String? = null,

	@ApiModelProperty(value = "")
	var context: String? = null,

	@ApiModelProperty(value = "")
	var rollbackInfo: String? = null,

	@ApiModelProperty(value = "")
	var logStatus: Int? = null,

	@ApiModelProperty(value = "")
	var logCreated: LocalDateTime? = null,

	@ApiModelProperty(value = "")
	var logModified: LocalDateTime? = null,

	@ApiModelProperty(value = "")
	var ext: String? = null,


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
