package com.github.ware.entity

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
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:23
 */
@Table("wms_ware_sku")
@ApiModel
data class WareSku(

    @ApiModelProperty(value = "id")
    @Id
    @get:JvmName("deprecate")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:Null(groups = [AddGroup::class])
    @field:NotNull(groups = [UpdateGroup::class])
    var id: Long? = null,

    @ApiModelProperty(value = "sku_id")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var skuId: Long? = null,

    @ApiModelProperty(value = "仓库id")
    @JsonSerialize(using = ToStringSerializer::class)
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var wareId: Long? = null,

    @ApiModelProperty(value = "库存数")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    @field:Min(value = 0, groups = [AddGroup::class, UpdateGroup::class])
    var stock: Int? = null,

    @ApiModelProperty(value = "sku_name")
    @field:NotNull(groups = [AddGroup::class, UpdateGroup::class])
    var skuName: String? = null,

    @ApiModelProperty(value = "锁定库存")
    var stockLocked: Int? = null,


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
