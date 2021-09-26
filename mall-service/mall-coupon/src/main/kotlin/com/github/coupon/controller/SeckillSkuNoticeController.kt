package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.SeckillSkuNotice
import com.github.coupon.service.SeckillSkuNoticeService
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
@RequestMapping("coupon/seckillskunotice")
@Api
class SeckillSkuNoticeController {

    @Autowired
    lateinit var seckillSkuNoticeService: SeckillSkuNoticeService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val seckillSkuNotice = seckillSkuNoticeService.getById(id)
        return resultSuccess().put("seckillSkuNotice", seckillSkuNotice)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody seckillSkuNotice: SeckillSkuNotice): ResultDto {
        seckillSkuNoticeService.saveOrUpdate(seckillSkuNotice)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody seckillSkuNotice: SeckillSkuNotice): ResultDto {
        seckillSkuNoticeService.saveOrUpdate(seckillSkuNotice)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        seckillSkuNoticeService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val seckillSkuNotices = seckillSkuNoticeService.getAll()
        return resultSuccess().put("seckillSkuNotice", seckillSkuNotices.toList())
    }
}
