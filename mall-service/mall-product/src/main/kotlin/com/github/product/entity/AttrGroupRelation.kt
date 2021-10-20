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
@Table("pms_attr_group_relation")
@ApiModel
data class AttrGroupRelation(

    @ApiModelProperty(value = "id")
    @Id
    @get:JvmName("deprecate")
    @JsonSerialize(using = ToStringSerializer::class)
    var id: Long? = null,

    @ApiModelProperty(value = "属性id")
    @JsonSerialize(using = ToStringSerializer::class)
    var attrId: Long? = null,

    @ApiModelProperty(value = "属性分组id")
    @JsonSerialize(using = ToStringSerializer::class)
    var attrGroupId: Long? = null,

    @ApiModelProperty(value = "属性组内排序")
    var attrSort: Int? = null,


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
