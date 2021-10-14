package com.github.member.entity

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
import java.math.BigDecimal

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:43:36
 */
@Table("ums_member_statistics_info")
@ApiModel
data class MemberStatisticsInfo (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	@JsonSerialize(using = ToStringSerializer::class)
	var id: Long? = null,

	@ApiModelProperty(value = "会员id")
	@JsonSerialize(using = ToStringSerializer::class)
	var memberId: Long? = null,

	@ApiModelProperty(value = "累计消费金额")
	var consumeAmount: BigDecimal? = null,

	@ApiModelProperty(value = "累计优惠金额")
	var couponAmount: BigDecimal? = null,

	@ApiModelProperty(value = "订单数量")
	var orderCount: Int? = null,

	@ApiModelProperty(value = "优惠券数量")
	var couponCount: Int? = null,

	@ApiModelProperty(value = "评价数")
	var commentCount: Int? = null,

	@ApiModelProperty(value = "退货数量")
	var returnOrderCount: Int? = null,

	@ApiModelProperty(value = "登录次数")
	var loginCount: Int? = null,

	@ApiModelProperty(value = "关注数量")
	var attendCount: Int? = null,

	@ApiModelProperty(value = "粉丝数量")
	var fansCount: Int? = null,

	@ApiModelProperty(value = "收藏的商品数量")
	var collectProductCount: Int? = null,

	@ApiModelProperty(value = "收藏的专题活动数量")
	var collectSubjectCount: Int? = null,

	@ApiModelProperty(value = "收藏的评论数量")
	var collectCommentCount: Int? = null,

	@ApiModelProperty(value = "邀请的朋友数量")
	var inviteFriendCount: Int? = null,


) : Serializable, Persistable<Long> {
	@JsonIgnore
	override fun isNew(): Boolean {
		return if (id == null) {
			id = IdUtil.getSnowflake().nextId()
			true
		} else false
	}
	@JsonSerialize(using = ToStringSerializer::class)
	override fun getId(): Long? = id
}
