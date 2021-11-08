package com.github.product.feign

import com.github.dto.ResultDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-06-20:41
 */
@ReactiveFeignClient("mall-ware")
interface WareFeign {

    @PostMapping("ware/waresku/has-stock")
    fun checkSkuStock(@RequestBody skuIds: List<Long>): Mono<ResultDto>
}
