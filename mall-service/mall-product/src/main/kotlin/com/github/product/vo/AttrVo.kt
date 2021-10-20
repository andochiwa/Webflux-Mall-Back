package com.github.product.vo

import com.github.vaild.AddGroup
import com.github.vaild.UpdateGroup
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Range
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
@ApiModel("attr vo")
data class AttrVo(

    @ApiModelProperty(value = "属性id")
    @field:Null(groups = [AddGroup::class])
    @field:NotNull(groups = [UpdateGroup::class])
    var id: Long? = null,

    @ApiModelProperty(value = "属性名")
    @field:NotBlank(groups = [AddGroup::class, UpdateGroup::class])
    var attrName: String? = null,

    @ApiModelProperty(value = "是否需要检索[0-不需要，1-需要]")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    @field:Range(min = 0, max = 1, groups = [AddGroup::class, UpdateGroup::class])
    var searchType: Int? = null,

    @ApiModelProperty(value = "属性图标")
    var icon: String? = null,

    @ApiModelProperty(value = "可选值列表[用逗号分隔]")
    var valueSelect: String? = null,

    @ApiModelProperty(value = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    @field:Range(min = 0, max = 2, groups = [AddGroup::class, UpdateGroup::class])
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var attrType: Int? = null,

    @ApiModelProperty(value = "启用状态[0 - 禁用，1 - 启用]")
    @field:Range(min = 0, max = 1, groups = [AddGroup::class, UpdateGroup::class])
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var enable: Long? = null,

    @ApiModelProperty(value = "所属分类")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var catelogId: Long? = null,

    @ApiModelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    @field:Range(min = 0, max = 1, groups = [AddGroup::class, UpdateGroup::class])
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var showDesc: Int? = null,

    @ApiModelProperty(value = "分组id")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var attrGroupId: Long? = null,

    ) : Serializable {
}
