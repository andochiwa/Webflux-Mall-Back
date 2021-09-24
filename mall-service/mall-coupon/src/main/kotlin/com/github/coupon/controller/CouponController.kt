package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.Coupon
import com.github.coupon.service.CouponService
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
@RequestMapping("coupon/coupon")
@Api
class CouponController {

    @Autowired
    lateinit var couponService: CouponService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val coupon = couponService.getById(id)
        return resultSuccess().put("coupon", coupon)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody coupon: Coupon): ResultDto {
        couponService.saveOrUpdate(coupon)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody coupon: Coupon): ResultDto {
        couponService.saveOrUpdate(coupon)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        couponService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val coupons = couponService.getAll()
        return resultSuccess().put("coupon", coupons.toList())
    }
}
