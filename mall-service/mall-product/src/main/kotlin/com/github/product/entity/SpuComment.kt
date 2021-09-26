package com.github.product.entity

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
 * @date 2021-09-27 04:43:25
 */
@Table("pms_spu_comment")
@ApiModel
data class SpuComment (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "sku_id")
	var skuId: Long? = null,

	@ApiModelProperty(value = "spu_id")
	var spuId: Long? = null,

	@ApiModelProperty(value = "商品名字")
	var spuName: String? = null,

	@ApiModelProperty(value = "会员昵称")
	var memberNickName: String? = null,

	@ApiModelProperty(value = "星级")
	var star: Int? = null,

	@ApiModelProperty(value = "会员ip")
	var memberIp: String? = null,

	@ApiModelProperty(value = "创建时间")
	var createTime: LocalDateTime? = null,

	@ApiModelProperty(value = "显示状态[0-不显示，1-显示]")
	var showStatus: Int? = null,

	@ApiModelProperty(value = "购买时属性组合")
	var spuAttributes: String? = null,

	@ApiModelProperty(value = "点赞数")
	var likesCount: Int? = null,

	@ApiModelProperty(value = "回复数")
	var replyCount: Int? = null,

	@ApiModelProperty(value = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
	var resources: String? = null,

	@ApiModelProperty(value = "内容")
	var content: String? = null,

	@ApiModelProperty(value = "用户头像")
	var memberIcon: String? = null,

	@ApiModelProperty(value = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
	var commentType: Int? = null,


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
