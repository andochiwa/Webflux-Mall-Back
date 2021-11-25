package com.github.product.service

import cn.hutool.core.lang.UUID
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.ReactiveStringRedisTemplate

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-13-02:12
 */
@SpringBootTest
class Test {

    @Autowired
    lateinit var stringRedisTemplate: ReactiveStringRedisTemplate

    @Autowired
    lateinit var skuInfoService: SkuInfoService

    @Test
    fun test() {
        val opsForValue = stringRedisTemplate.opsForValue()
        runBlocking {
            opsForValue.set("hello", "world_${UUID.fastUUID()}").awaitSingle()

            println("data = ${opsForValue.get("hello").awaitSingle()}")
        }
    }

    @Test
    fun getSkuItemTest() {
        runBlocking {
            println(skuInfoService.getSkuItem(1451894371813515264))
        }
    }
}
