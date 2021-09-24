package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.SkuLadder
import com.github.coupon.service.SkuLadderService
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
@RequestMapping("coupon/skuladder")
@Api
class SkuLadderController {

    @Autowired
    lateinit var skuLadderService: SkuLadderService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val skuLadder = skuLadderService.getById(id)
        return resultSuccess().put("skuLadder", skuLadder)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody skuLadder: SkuLadder): ResultDto {
        skuLadderService.saveOrUpdate(skuLadder)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody skuLadder: SkuLadder): ResultDto {
        skuLadderService.saveOrUpdate(skuLadder)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        skuLadderService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val skuLadders = skuLadderService.getAll()
        return resultSuccess().put("skuLadder", skuLadders.toList())
    }
}
