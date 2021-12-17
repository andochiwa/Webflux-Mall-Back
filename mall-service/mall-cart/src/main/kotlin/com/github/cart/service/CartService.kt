package com.github.cart.service

import com.github.cart.vo.CartVo
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-11-05:32
 */
@Service
class CartService {
    val CART_PREFIX: String = "chiwamall:cart:"

    @Autowired
    lateinit var redisTemplate: ReactiveRedisTemplate<String, Any>

    suspend fun addToCart(skuId: Long, num: Int): CartVo.CartItem {
        val hashOperations = redisTemplate.opsForHash<String, String>()
        val token = Mono.deferContextual { ctx -> Mono.just<String>(ctx["token"]) }.awaitSingleOrNull()
        val cartKey = "$CART_PREFIX$token"

        return CartVo.CartItem()

    }
}
