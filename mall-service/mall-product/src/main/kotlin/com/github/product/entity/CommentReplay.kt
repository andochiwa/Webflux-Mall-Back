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
@Table("pms_comment_replay")
@ApiModel
data class CommentReplay (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,
	@ApiModelProperty(value = "评论id")
	var commentId: Long? = null,
	@ApiModelProperty(value = "回复id")
	var replyId: Long? = null,

): Serializable, Persistable<Long> {
	@JsonIgnore
	override fun isNew(): Boolean {
		return if (id == null) {
			id = IdUtil.getSnowflake().nextId()
			true
		} else false
	}
	override fun getId(): Long? = id
}
