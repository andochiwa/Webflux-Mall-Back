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

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:43:25
 */
@Table("pms_sku_images")
@ApiModel
data class SkuImages(

    @ApiModelProperty(value = "id")
    @Id
    @get:JvmName("deprecate")
    @JsonSerialize(using = ToStringSerializer::class)
    var id: Long? = null,

    @ApiModelProperty(value = "sku_id")
    @JsonSerialize(using = ToStringSerializer::class)
    var skuId: Long? = null,

    @ApiModelProperty(value = "图片地址")
    var imgUrl: String? = null,

    @ApiModelProperty(value = "排序")
    var imgSort: Int? = null,

    @ApiModelProperty(value = "默认图[0 - 不是默认图，1 - 是默认图]")
    var defaultImg: Int? = null,


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
