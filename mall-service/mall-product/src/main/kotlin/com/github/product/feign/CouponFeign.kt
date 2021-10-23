package com.github.product.feign

import com.github.dto.ResultDto
import com.github.to.SkuFullReductionTo
import com.github.to.SpuBoundTo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

/**
 * @author Andochiwa
 * @version 1.0
 * @since 10-23-18:29
 */
@ReactiveFeignClient("mall-coupon")
interface CouponFeign {

    @PostMapping("coupon/spubounds")
    fun saveSpuBounds(@RequestBody spuBoundTo: SpuBoundTo): Mono<ResultDto>

    @PostMapping("coupon/skufullreduction/saveInfo")
    fun saveSkuReduction(@RequestBody skuFullReductionTo: List<SkuFullReductionTo>): Mono<ResultDto>
}
