package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.CouponHistory
import com.github.coupon.service.CouponHistoryService
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
@RequestMapping("coupon/couponhistory")
@Api
class CouponHistoryController {

    @Autowired
    lateinit var couponHistoryService: CouponHistoryService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val couponHistory = couponHistoryService.getById(id)
        return resultSuccess().put("couponHistory", couponHistory)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody couponHistory: CouponHistory): ResultDto {
        couponHistoryService.saveOrUpdate(couponHistory)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody couponHistory: CouponHistory): ResultDto {
        couponHistoryService.saveOrUpdate(couponHistory)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        couponHistoryService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val couponHistorys = couponHistoryService.getAll()
        return resultSuccess().put("couponHistory", couponHistorys.toList())
    }
}
