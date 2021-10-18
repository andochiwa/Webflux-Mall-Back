package com.github.product.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
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
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Table("pms_category_brand_relation")
@ApiModel
data class CategoryBrandRelation(

    @ApiModelProperty(value = "")
    @Id
    @get:JvmName("deprecate")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:NotNull(groups = [UpdateGroup::class])
    @field:Null(groups = [AddGroup::class])
    var id: Long? = null,

    @ApiModelProperty(value = "品牌id")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var brandId: Long? = null,

    @ApiModelProperty(value = "分类id")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var catelogId: Long? = null,

    @ApiModelProperty(value = "品牌名字")
    var brandName: String? = null,

    @ApiModelProperty(value = "分类名字")
    var catelogName: String? = null,


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
