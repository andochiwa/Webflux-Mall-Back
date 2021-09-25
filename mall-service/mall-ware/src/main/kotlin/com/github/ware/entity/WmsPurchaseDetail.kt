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

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:24
 */
@Table("wms_purchase_detail")
@ApiModel
data class WmsPurchaseDetail (

	@ApiModelProperty(value = "")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "采购单id")
	var purchaseId: Long? = null,

	@ApiModelProperty(value = "采购商品id")
	var skuId: Long? = null,

	@ApiModelProperty(value = "采购数量")
	var skuNum: Int? = null,

	@ApiModelProperty(value = "采购金额")
	var skuPrice: BigDecimal? = null,

	@ApiModelProperty(value = "仓库id")
	var wareId: Long? = null,

	@ApiModelProperty(value = "状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
	var status: Int? = null,


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
