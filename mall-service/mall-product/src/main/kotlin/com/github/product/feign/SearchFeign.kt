package com.github.product.feign

import com.github.dto.ResultDto
import com.github.to.es.SkuEsTo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-07-00:28
 */
@ReactiveFeignClient("mall-search")
interface SearchFeign {

    @PostMapping("search/product")
    fun putOnProduct(@RequestBody skuEsToList: List<SkuEsTo>): Mono<ResultDto>
}
