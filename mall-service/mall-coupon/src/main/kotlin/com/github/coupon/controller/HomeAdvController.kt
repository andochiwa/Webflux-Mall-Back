package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.HomeAdv
import com.github.coupon.service.HomeAdvService
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
@RequestMapping("coupon/homeadv")
@Api
class HomeAdvController {

    @Autowired
    lateinit var homeAdvService: HomeAdvService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val homeAdv = homeAdvService.getById(id)
        return resultSuccess().put("homeAdv", homeAdv)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody homeAdv: HomeAdv): ResultDto {
        homeAdvService.saveOrUpdate(homeAdv)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody homeAdv: HomeAdv): ResultDto {
        homeAdvService.saveOrUpdate(homeAdv)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        homeAdvService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val homeAdvs = homeAdvService.getAll()
        return resultSuccess().put("homeAdv", homeAdvs.toList())
    }
}
