package com.github.cart.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-11-05:32
 */
@Service
class CartService {

    @Autowired
    lateinit var redisTemplate: ReactiveRedisTemplate<String, Any>
}
