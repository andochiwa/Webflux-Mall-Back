package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.SeckillSkuRelation
import com.github.coupon.service.SeckillSkuRelationService
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
@RequestMapping("coupon/seckillskurelation")
@Api
class SeckillSkuRelationController {

    @Autowired
    lateinit var seckillSkuRelationService: SeckillSkuRelationService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val seckillSkuRelation = seckillSkuRelationService.getById(id)
        return resultSuccess().put("seckillSkuRelation", seckillSkuRelation)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody seckillSkuRelation: SeckillSkuRelation): ResultDto {
        seckillSkuRelationService.saveOrUpdate(seckillSkuRelation)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody seckillSkuRelation: SeckillSkuRelation): ResultDto {
        seckillSkuRelationService.saveOrUpdate(seckillSkuRelation)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        seckillSkuRelationService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val seckillSkuRelations = seckillSkuRelationService.getAll()
        return resultSuccess().put("seckillSkuRelation", seckillSkuRelations.toList())
    }
}
