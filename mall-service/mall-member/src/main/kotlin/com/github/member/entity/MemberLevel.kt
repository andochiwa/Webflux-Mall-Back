package com.github.member.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.github.vaild.AddGroup
import com.github.vaild.UpdateGroup
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Range
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:43:36
 */
@Table("ums_member_level")
@ApiModel
data class MemberLevel(

    @ApiModelProperty(value = "id")
    @Id
    @get:JvmName("deprecate")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:Null(groups = [AddGroup::class])
    @field:NotNull(groups = [UpdateGroup::class])
    var id: Long? = null,

    @ApiModelProperty(value = "等级名称")
    @field:NotBlank(groups = [AddGroup::class, UpdateGroup::class])
    var name: String? = null,

    @ApiModelProperty(value = "等级需要的成长值")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var growthPoint: Int? = null,

    @ApiModelProperty(value = "是否为默认等级[0->不是；1->是]")
    @field:Range(min = 0, max = 1, groups = [AddGroup::class, UpdateGroup::class])
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var defaultStatus: Int? = null,

    @ApiModelProperty(value = "免运费标准")
    var freeFreightPoint: BigDecimal? = null,

    @ApiModelProperty(value = "每次评价获取的成长值")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var commentGrowthPoint: Int? = null,

    @ApiModelProperty(value = "是否有免邮特权")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var privilegeFreeFreight: Int? = null,

    @ApiModelProperty(value = "是否有会员价格特权")
    @field:Range(min = 0, max = 1, groups = [AddGroup::class, UpdateGroup::class])
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var privilegeMemberPrice: Int? = null,

    @ApiModelProperty(value = "是否有生日特权")
    @field:Range(min = 0, max = 1, groups = [AddGroup::class, UpdateGroup::class])
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
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

    @JsonSerialize(using = ToStringSerializer::class)
    override fun getId(): Long? = id
}
