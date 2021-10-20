package com.github.product.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import com.github.vaild.AddGroup
import com.github.vaild.UpdateGroup
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Table("pms_attr")
@ApiModel
data class Attr(

    @ApiModelProperty(value = "属性id")
    @Id
    @get:JvmName("deprecate")
    @JsonSerialize(using = ToStringSerializer::class)
    @JsonProperty("id")
    @field:Null(groups = [AddGroup::class])
    @field:NotNull(groups = [UpdateGroup::class])
    var attrId: Long? = null,

    @ApiModelProperty(value = "属性名")
    @field:NotBlank(groups = [AddGroup::class, UpdateGroup::class])
    var attrName: String? = null,

    @ApiModelProperty(value = "是否需要检索[0-不需要，1-需要]")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var searchType: Int? = null,

    @ApiModelProperty(value = "属性图标")
    var icon: String? = null,

    @ApiModelProperty(value = "可选值列表[用逗号分隔]")
    var valueSelect: String? = null,

    @ApiModelProperty(value = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var attrType: Int? = null,

    @ApiModelProperty(value = "启用状态[0 - 禁用，1 - 启用]")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var enable: Long? = null,

    @ApiModelProperty(value = "所属分类")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var catelogId: Long? = null,

    @ApiModelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var showDesc: Int? = null,


    ) : Serializable, Persistable<Long> {
    @JsonIgnore
    override fun isNew(): Boolean {
        return if (attrId == null) {
            attrId = IdUtil.getSnowflake().nextId()
            true
        } else false
    }

    @JsonSerialize(using = ToStringSerializer::class)
    override fun getId(): Long? = attrId
}
