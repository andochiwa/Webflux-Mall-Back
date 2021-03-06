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
import org.hibernate.validator.constraints.Range
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Table("pms_category")
@ApiModel
data class Category(

    @ApiModelProperty(value = "分类id")
    @Id
    @get:JvmName("deprecate")
    @JsonSerialize(using = ToStringSerializer::class)
    @JsonProperty("id")
    @field:NotNull(groups = [UpdateGroup::class])
    @field:Null(groups = [AddGroup::class])
    var catId: Long? = null,

    @ApiModelProperty(value = "分类名称")
    var name: String? = null,

    @ApiModelProperty(value = "父分类id")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var parentCid: Long? = null,

    @ApiModelProperty(value = "层级")
    @field:Min(value = 0, groups = [AddGroup::class, UpdateGroup::class])
    var catLevel: Int? = null,

    @ApiModelProperty(value = "是否显示[0-不显示，1显示]")
    @field:Range(min = 0, max = 1, groups = [AddGroup::class, UpdateGroup::class])
    var showStatus: Int? = null,

    @ApiModelProperty(value = "排序")
    var sort: Int = 0,

    @ApiModelProperty(value = "图标地址")
    var icon: String? = null,

    @ApiModelProperty(value = "计量单位")
    var productUnit: String? = null,

    @ApiModelProperty(value = "商品数量")
    var productCount: Int? = null,

    @ApiModelProperty(value = "子分类")
    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var children: List<Category>? = null,


    ) : Serializable, Persistable<Long> {
    @PersistenceConstructor
    constructor(
        catId: Long?,
        name: String?,
        parentCid: Long?,
        catLevel: Int?,
        showStatus: Int?,
        sort: Int = 0,
        icon: String?,
        productUnit: String?,
        productCount: Int?
    ) : this(catId, name, parentCid, catLevel, showStatus, sort, icon, productUnit, productCount, null)

    @JsonIgnore
    override fun isNew(): Boolean {
        return if (catId == null) {
            catId = IdUtil.getSnowflake().nextId()
            true
        } else false
    }

    @JsonSerialize(using = ToStringSerializer::class)
    override fun getId(): Long? = catId
}
