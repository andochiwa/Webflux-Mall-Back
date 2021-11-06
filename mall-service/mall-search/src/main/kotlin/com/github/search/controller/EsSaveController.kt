package com.github.search.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.search.entity.SkuEntity
import com.github.search.service.EsSaveService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-06-22:01
 */
@RestController("/search")
@Api
class EsSaveController {

    @Autowired
    lateinit var esSaveService: EsSaveService

    @PostMapping("/product")
    @ApiOperation("put on product")
    suspend fun putOnProduct(@RequestBody skuEntityList: List<SkuEntity>): ResultDto {
        esSaveService.putOnProduct(skuEntityList)
        return resultSuccess()
    }
}
