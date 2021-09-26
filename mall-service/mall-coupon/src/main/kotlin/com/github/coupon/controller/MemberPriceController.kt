package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.MemberPrice
import com.github.coupon.service.MemberPriceService
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
@RequestMapping("coupon/memberprice")
@Api
class MemberPriceController {

    @Autowired
    lateinit var memberPriceService: MemberPriceService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val memberPrice = memberPriceService.getById(id)
        return resultSuccess().put("memberPrice", memberPrice)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody memberPrice: MemberPrice): ResultDto {
        memberPriceService.saveOrUpdate(memberPrice)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody memberPrice: MemberPrice): ResultDto {
        memberPriceService.saveOrUpdate(memberPrice)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        memberPriceService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val memberPrices = memberPriceService.getAll()
        return resultSuccess().put("memberPrice", memberPrices.toList())
    }
}
