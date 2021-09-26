package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.SkuSaleAttrValue
import com.github.product.service.SkuSaleAttrValueService
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
@RequestMapping("product/skusaleattrvalue")
@Api
class SkuSaleAttrValueController {

    @Autowired
    lateinit var skuSaleAttrValueService: SkuSaleAttrValueService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val skuSaleAttrValue = skuSaleAttrValueService.getById(id)
        return resultSuccess().put("skuSaleAttrValue", skuSaleAttrValue)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody skuSaleAttrValue: SkuSaleAttrValue): ResultDto {
        skuSaleAttrValueService.saveOrUpdate(skuSaleAttrValue)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody skuSaleAttrValue: SkuSaleAttrValue): ResultDto {
        skuSaleAttrValueService.saveOrUpdate(skuSaleAttrValue)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        skuSaleAttrValueService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val skuSaleAttrValues = skuSaleAttrValueService.getAll()
        return resultSuccess().put("skuSaleAttrValue", skuSaleAttrValues.toList())
    }
}
