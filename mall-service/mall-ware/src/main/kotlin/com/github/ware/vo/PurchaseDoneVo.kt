package com.github.ware.vo

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotNull

data class PurchaseDoneVo(
    @field:NotNull
    var id: Long? = null,

    var items: List<PurchaseItem>,
) {
    data class PurchaseItem(
        @NotNull
        var itemId: Long? = null,

        @NotNull
        @Range(min = 0, max = 4)
        var status: Int? = null,

        var reason: String? = null,
    )
}
