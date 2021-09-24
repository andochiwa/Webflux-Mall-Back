package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.CouponSpuCategoryRelation
import com.github.coupon.service.CouponSpuCategoryRelationService
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
@RequestMapping("coupon/couponspucategoryrelation")
@Api
class CouponSpuCategoryRelationController {

    @Autowired
    lateinit var couponSpuCategoryRelationService: CouponSpuCategoryRelationService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val couponSpuCategoryRelation = couponSpuCategoryRelationService.getById(id)
        return resultSuccess().put("couponSpuCategoryRelation", couponSpuCategoryRelation)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody couponSpuCategoryRelation: CouponSpuCategoryRelation): ResultDto {
        couponSpuCategoryRelationService.saveOrUpdate(couponSpuCategoryRelation)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody couponSpuCategoryRelation: CouponSpuCategoryRelation): ResultDto {
        couponSpuCategoryRelationService.saveOrUpdate(couponSpuCategoryRelation)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        couponSpuCategoryRelationService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val couponSpuCategoryRelations = couponSpuCategoryRelationService.getAll()
        return resultSuccess().put("couponSpuCategoryRelation", couponSpuCategoryRelations.toList())
    }
}
