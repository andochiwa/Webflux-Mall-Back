package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.SeckillSession
import com.github.coupon.service.SeckillSessionService
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
@RequestMapping("coupon/seckillsession")
@Api
class SeckillSessionController {

    @Autowired
    lateinit var seckillSessionService: SeckillSessionService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val seckillSession = seckillSessionService.getById(id)
        return resultSuccess().put("seckillSession", seckillSession)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody seckillSession: SeckillSession): ResultDto {
        seckillSessionService.saveOrUpdate(seckillSession)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody seckillSession: SeckillSession): ResultDto {
        seckillSessionService.saveOrUpdate(seckillSession)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        seckillSessionService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val seckillSessions = seckillSessionService.getAll()
        return resultSuccess().put("seckillSession", seckillSessions.toList())
    }
}
