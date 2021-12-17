package com.github.cart.web

import com.github.cart.service.CartService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import reactor.core.publisher.Mono

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-11-05:56
 */
@Controller
class CartWebController {

    @Autowired
    lateinit var cartService: CartService

    @GetMapping("cart", "cart.html")
    suspend fun cartListPage(): String {
        println(Mono.deferContextual { ctx -> Mono.just<String>(ctx["token"]) }.awaitSingle())
        return "cartList"
    }

    @GetMapping("addToCart")
    suspend fun addToCart(@RequestParam("skuId") skuId: Long, @RequestParam("num") num: Int, model: Model): String {
        val cartItem = cartService.addToCart(skuId, num)
        model.addAttribute("result", cartItem)
        return "success"
    }
}
