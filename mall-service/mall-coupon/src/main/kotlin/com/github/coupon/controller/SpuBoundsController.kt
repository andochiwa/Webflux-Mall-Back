package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.SpuBounds
import com.github.coupon.service.SpuBoundsService
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
@RequestMapping("coupon/spubounds")
@Api
class SpuBoundsController {

    @Autowired
    lateinit var spuBoundsService: SpuBoundsService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val spuBounds = spuBoundsService.getById(id)
        return resultSuccess().put("spuBounds", spuBounds)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody spuBounds: SpuBounds): ResultDto {
        spuBoundsService.saveOrUpdate(spuBounds)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody spuBounds: SpuBounds): ResultDto {
        spuBoundsService.saveOrUpdate(spuBounds)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        spuBoundsService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val spuBoundss = spuBoundsService.getAll()
        return resultSuccess().put("spuBounds", spuBoundss.toList())
    }
}
