package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.SkuImages
import com.github.product.service.SkuImagesService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:43:25
 */
@RestController
@RequestMapping("product/skuimages")
@Api
class SkuImagesController {

    @Autowired
    lateinit var skuImagesService: SkuImagesService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val skuImages = skuImagesService.getById(id)
        return resultSuccess().put("skuImages", skuImages)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody skuImages: SkuImages): ResultDto {
        skuImagesService.saveOrUpdate(skuImages)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody skuImages: SkuImages): ResultDto {
        skuImagesService.saveOrUpdate(skuImages)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        skuImagesService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val skuImagess = skuImagesService.getAll()
        return resultSuccess().put("skuImages", skuImagess.toList())
    }
}
