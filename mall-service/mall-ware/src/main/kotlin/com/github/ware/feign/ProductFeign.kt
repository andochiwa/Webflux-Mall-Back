package com.github.ware.feign

import com.github.dto.ResultDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

/**
 * @author Andochiwa
 * @version 1.0
 * @since 10-25-21:47
 */
@ReactiveFeignClient("mall-product")
interface ProductFeign {

    @GetMapping("/product/skuinfo/{id}")
    fun getSkuInfoById(@PathVariable("id") id: Long): Mono<ResultDto>
}
