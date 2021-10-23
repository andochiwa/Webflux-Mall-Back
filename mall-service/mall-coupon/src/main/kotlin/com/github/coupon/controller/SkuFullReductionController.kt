package com.github.coupon.controller

import com.github.coupon.entity.SkuFullReduction
import com.github.coupon.service.SkuFullReductionService
import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.to.SkuFullReductionTo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 23:39:59
 */
@RestController
@RequestMapping("coupon/skufullreduction")
@Api
class SkuFullReductionController {

    @Autowired
    lateinit var skuFullReductionService: SkuFullReductionService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val skuFullReduction = skuFullReductionService.getById(id)
        return resultSuccess().put("skuFullReduction", skuFullReduction)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody skuFullReduction: SkuFullReduction): ResultDto {
        skuFullReductionService.saveOrUpdate(skuFullReduction)
        return resultSuccess()
    }

    @PostMapping("saveInfo")
    @ApiOperation("save all sku reduction info")
    suspend fun saveInfo(@RequestBody skuFullReductionTo: List<SkuFullReductionTo>): ResultDto {
        skuFullReductionService.saveSkuReduction(skuFullReductionTo)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody skuFullReduction: SkuFullReduction): ResultDto {
        skuFullReductionService.saveOrUpdate(skuFullReduction)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        skuFullReductionService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val skuFullReductions = skuFullReductionService.getAll()
        return resultSuccess().put("skuFullReduction", skuFullReductions.toList())
    }
}
