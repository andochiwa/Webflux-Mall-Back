package com.github.cart.web

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-11-05:56
 */
@Controller
class CartController {

    @GetMapping("cart", "cart.html")
    suspend fun cartListPage(): String {
        println(Mono.deferContextual { ctx -> Mono.just<String>(ctx.get("token")) }.awaitSingle())
        return "cartList"
    }
}
