package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.SpuImages
import com.github.product.service.SpuImagesService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@RestController
@RequestMapping("product/spuimages")
@Api
class SpuImagesController {

    @Autowired
    lateinit var spuImagesService: SpuImagesService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val spuImages = spuImagesService.getById(id)
        return resultSuccess().put("spuImages", spuImages)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody spuImages: SpuImages): ResultDto {
        spuImagesService.saveOrUpdate(spuImages)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody spuImages: SpuImages): ResultDto {
        spuImagesService.saveOrUpdate(spuImages)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        spuImagesService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val spuImagess = spuImagesService.getAll()
        return resultSuccess().put("spuImages", spuImagess.toList())
    }
}
