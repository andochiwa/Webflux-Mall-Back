package com.github.product.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.github.vaild.AddGroup
import com.github.vaild.UpdateGroup
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Table("pms_attr_group")
@ApiModel
data class AttrGroup(

    @ApiModelProperty(value = "分组id")
    @Id
    @get:JvmName("deprecate")
    @JsonSerialize(using = ToStringSerializer::class)
    @JsonProperty("id")
    @field:Null(groups = [AddGroup::class])
    @field:NotNull(groups = [UpdateGroup::class])
    var attrGroupId: Long? = null,

    @ApiModelProperty(value = "组名")
    @field:NotBlank(groups = [AddGroup::class, UpdateGroup::class])
    var attrGroupName: String? = null,

    @ApiModelProperty(value = "排序")
    @field:Min(value = 0, groups = [AddGroup::class, UpdateGroup::class])
    var sort: Int? = null,

    @ApiModelProperty(value = "描述")
    var description: String? = null,

    @ApiModelProperty(value = "组图标")
    var icon: String? = null,

    @ApiModelProperty(value = "所属分类id")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var catelogId: Long? = null,

    @ApiModelProperty(value = "父子所有id")
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var catelogPath: List<String>? = null


) : Serializable, Persistable<Long> {
    @PersistenceConstructor
    constructor(
        attrGroupId: Long?,
        attrGroupName: String?,
        sort: Int?,
        description: String?,
        icon: String?,
        catelogId: Long?
    ) : this(attrGroupId, attrGroupName, sort, description, icon, catelogId, null)

    @JsonIgnore
    override fun isNew(): Boolean {
        return if (attrGroupId == null) {
            attrGroupId = IdUtil.getSnowflake().nextId()
            true
        } else false
    }

    @JsonSerialize(using = ToStringSerializer::class)
    override fun getId(): Long? = attrGroupId
}
