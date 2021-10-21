package com.github.product.vo

import com.github.vaild.DeleteGroup
import javax.validation.constraints.NotNull

data class AttrAndGroupRelationVo(
    @NotNull(groups = [DeleteGroup::class])
    var attrId: Long? = null,

    @NotNull(groups = [DeleteGroup::class])
    var attrGroupId: Long? = null,
)
