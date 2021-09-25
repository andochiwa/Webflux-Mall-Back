package com.github.order.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.order.entity.RefundInfo
import com.github.order.service.RefundInfoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:18:13
 */
@RestController
@RequestMapping("order/refundinfo")
@Api
class RefundInfoController {

    @Autowired
    lateinit var refundInfoService: RefundInfoService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val refundInfo = refundInfoService.getById(id)
        return resultSuccess().put("refundInfo", refundInfo)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody refundInfo: RefundInfo): ResultDto {
        refundInfoService.saveOrUpdate(refundInfo)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody refundInfo: RefundInfo): ResultDto {
        refundInfoService.saveOrUpdate(refundInfo)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        refundInfoService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val refundInfos = refundInfoService.getAll()
        return resultSuccess().put("refundInfo", refundInfos.toList())
    }
}
