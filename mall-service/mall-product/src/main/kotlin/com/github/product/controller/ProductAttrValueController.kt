package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.ProductAttrValue
import com.github.product.service.ProductAttrValueService
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
@RequestMapping("product/productattrvalue")
@Api
class ProductAttrValueController {

    @Autowired
    lateinit var productAttrValueService: ProductAttrValueService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val productAttrValue = productAttrValueService.getById(id)
        return resultSuccess().put("productAttrValue", productAttrValue)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody productAttrValue: ProductAttrValue): ResultDto {
        productAttrValueService.saveOrUpdate(productAttrValue)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody productAttrValue: ProductAttrValue): ResultDto {
        productAttrValueService.saveOrUpdate(productAttrValue)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        productAttrValueService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val productAttrValues = productAttrValueService.getAll()
        return resultSuccess().put("productAttrValue", productAttrValues.toList())
    }
}
