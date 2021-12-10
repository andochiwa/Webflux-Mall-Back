package com.github.cart.vo

import java.math.BigDecimal

data class CartVo(
    var items: List<CartItem>,

    var reduce: BigDecimal = BigDecimal("0.00")
) {

    val countNum: Int
        get() = items.map(CartItem::count).reduce(Int::plus)

    val countType: Int
        get() = items.size

    val totalAmount: BigDecimal
        get() = items.map(CartItem::totalPrice).reduce(BigDecimal::add).minus(reduce)

    data class CartItem(
        var skuId: Long? = null,

        var check: Boolean = true,

        var title: String? = null,

        var image: String? = null,

        var skuAttrs: List<String>? = null,

        var price: BigDecimal? = null,

        var count: Int = 0,
    ) {
        val totalPrice: BigDecimal
            get() {
                return price?.multiply(BigDecimal(count.toString())) ?: BigDecimal("0")
            }
    }
}
