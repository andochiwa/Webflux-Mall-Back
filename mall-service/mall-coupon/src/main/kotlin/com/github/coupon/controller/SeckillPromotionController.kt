package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.SeckillPromotion
import com.github.coupon.service.SeckillPromotionService
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
@RequestMapping("coupon/seckillpromotion")
@Api
class SeckillPromotionController {

    @Autowired
    lateinit var seckillPromotionService: SeckillPromotionService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val seckillPromotion = seckillPromotionService.getById(id)
        return resultSuccess().put("seckillPromotion", seckillPromotion)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody seckillPromotion: SeckillPromotion): ResultDto {
        seckillPromotionService.saveOrUpdate(seckillPromotion)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody seckillPromotion: SeckillPromotion): ResultDto {
        seckillPromotionService.saveOrUpdate(seckillPromotion)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        seckillPromotionService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val seckillPromotions = seckillPromotionService.getAll()
        return resultSuccess().put("seckillPromotion", seckillPromotions.toList())
    }
}
