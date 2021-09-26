package com.github.coupon.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
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
 * @date 2021-09-27 04:41:21
 */
@Table("sms_member_price")
@ApiModel
data class MemberPrice (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "sku_id")
	var skuId: Long? = null,

	@ApiModelProperty(value = "会员等级id")
	var memberLevelId: Long? = null,

	@ApiModelProperty(value = "会员等级名")
	var memberLevelName: String? = null,

	@ApiModelProperty(value = "会员对应价格")
	var memberPrice: BigDecimal? = null,

	@ApiModelProperty(value = "可否叠加其他优惠[0-不可叠加优惠，1-可叠加]")
	var addOther: Int? = null,


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
