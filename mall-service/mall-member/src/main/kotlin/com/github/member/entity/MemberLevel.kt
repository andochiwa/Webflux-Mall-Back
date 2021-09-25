package com.github.member.entity

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
 * @date 2021-09-26 03:43:36
 */
@Table("ums_member_level")
@ApiModel
data class MemberLevel (

	@ApiModelProperty(value = "id")
	@Id
	@get:JvmName("deprecate")
	var id: Long? = null,

	@ApiModelProperty(value = "等级名称")
	var name: String? = null,

	@ApiModelProperty(value = "等级需要的成长值")
	var growthPoint: Int? = null,

	@ApiModelProperty(value = "是否为默认等级[0->不是；1->是]")
	var defaultStatus: Int? = null,

	@ApiModelProperty(value = "免运费标准")
	var freeFreightPoint: BigDecimal? = null,

	@ApiModelProperty(value = "每次评价获取的成长值")
	var commentGrowthPoint: Int? = null,

	@ApiModelProperty(value = "是否有免邮特权")
	var privilegeFreeFreight: Int? = null,

	@ApiModelProperty(value = "是否有会员价格特权")
	var privilegeMemberPrice: Int? = null,

	@ApiModelProperty(value = "是否有生日特权")
	var privilegeBirthday: Int? = null,

	@ApiModelProperty(value = "备注")
	var note: String? = null,


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
