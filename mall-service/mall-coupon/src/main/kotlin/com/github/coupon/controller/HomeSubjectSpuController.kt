package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.HomeSubjectSpu
import com.github.coupon.service.HomeSubjectSpuService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:41:21
 */
@RestController
@RequestMapping("coupon/homesubjectspu")
@Api
class HomeSubjectSpuController {

    @Autowired
    lateinit var homeSubjectSpuService: HomeSubjectSpuService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val homeSubjectSpu = homeSubjectSpuService.getById(id)
        return resultSuccess().put("homeSubjectSpu", homeSubjectSpu)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody homeSubjectSpu: HomeSubjectSpu): ResultDto {
        homeSubjectSpuService.saveOrUpdate(homeSubjectSpu)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody homeSubjectSpu: HomeSubjectSpu): ResultDto {
        homeSubjectSpuService.saveOrUpdate(homeSubjectSpu)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        homeSubjectSpuService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val homeSubjectSpus = homeSubjectSpuService.getAll()
        return resultSuccess().put("homeSubjectSpu", homeSubjectSpus.toList())
    }
}
