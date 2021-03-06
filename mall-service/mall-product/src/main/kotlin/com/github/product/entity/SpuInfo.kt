package com.github.product.entity

import cn.hutool.core.util.IdUtil
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Table("pms_spu_info")
@ApiModel
data class SpuInfo(

    @ApiModelProperty(value = "商品id")
    @Id
    @get:JvmName("deprecate")
    @JsonSerialize(using = ToStringSerializer::class)
    var id: Long? = null,

    @ApiModelProperty(value = "商品名称")
    var spuName: String? = null,

    @ApiModelProperty(value = "商品描述")
    var spuDescription: String? = null,

    @ApiModelProperty(value = "所属分类id")
    @JsonSerialize(using = ToStringSerializer::class)
    var catelogId: Long? = null,

    @ApiModelProperty(value = "品牌id")
    @JsonSerialize(using = ToStringSerializer::class)
    var brandId: Long? = null,

    @ApiModelProperty(value = "")
    var weight: BigDecimal? = null,

    @ApiModelProperty(value = "上架状态[0 - 新建，1 - 上架，2 - 下架]")
    var publishStatus: Int? = null,

    @ApiModelProperty(value = "")
    @JsonSerialize(using = ToStringSerializer::class)
    var createTime: LocalDateTime? = null,

    @ApiModelProperty(value = "")
    var updateTime: LocalDateTime? = null,
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
