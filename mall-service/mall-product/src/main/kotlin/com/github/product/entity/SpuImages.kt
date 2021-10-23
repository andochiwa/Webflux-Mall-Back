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
 * @date 2021-09-24 00:47:19
 */
@Table("pms_spu_images")
@ApiModel
data class SpuImages(

    @ApiModelProperty(value = "id")
    @Id
    @get:JvmName("deprecate")
    @JsonSerialize(using = ToStringSerializer::class)
    var id: Long? = null,

    @ApiModelProperty(value = "spu_id")
    @JsonSerialize(using = ToStringSerializer::class)
    var spuId: Long? = null,

    @ApiModelProperty(value = "图片地址")
    var imgUrl: String? = null,

    @ApiModelProperty(value = "顺序")
    var imgSort: Int? = null,


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
