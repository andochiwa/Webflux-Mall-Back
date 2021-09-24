package com.github.coupon.entity

import java.math.BigDecimal
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
 * @date 2021-09-24 23:39:59
 */
@Table("sms_seckill_sku_relation")
@ApiModel
data class SeckillSkuRelation (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "活动id")
	var promotionId: Long? = null,

	@ApiModelProperty(value = "活动场次id")
	var promotionSessionId: Long? = null,

	@ApiModelProperty(value = "商品id")
	var skuId: Long? = null,

	@ApiModelProperty(value = "秒杀价格")
	var seckillPrice: BigDecimal? = null,

	@ApiModelProperty(value = "秒杀总量")
	var seckillCount: BigDecimal? = null,

	@ApiModelProperty(value = "每人限购数量")
	var seckillLimit: BigDecimal? = null,

	@ApiModelProperty(value = "排序")
	var seckillSort: Int? = null,


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
