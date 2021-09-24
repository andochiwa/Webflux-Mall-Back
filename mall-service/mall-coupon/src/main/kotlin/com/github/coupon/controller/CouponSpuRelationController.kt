package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.CouponSpuRelation
import com.github.coupon.service.CouponSpuRelationService
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
@RequestMapping("coupon/couponspurelation")
@Api
class CouponSpuRelationController {

    @Autowired
    lateinit var couponSpuRelationService: CouponSpuRelationService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val couponSpuRelation = couponSpuRelationService.getById(id)
        return resultSuccess().put("couponSpuRelation", couponSpuRelation)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody couponSpuRelation: CouponSpuRelation): ResultDto {
        couponSpuRelationService.saveOrUpdate(couponSpuRelation)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody couponSpuRelation: CouponSpuRelation): ResultDto {
        couponSpuRelationService.saveOrUpdate(couponSpuRelation)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        couponSpuRelationService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val couponSpuRelations = couponSpuRelationService.getAll()
        return resultSuccess().put("couponSpuRelation", couponSpuRelations.toList())
    }
}
