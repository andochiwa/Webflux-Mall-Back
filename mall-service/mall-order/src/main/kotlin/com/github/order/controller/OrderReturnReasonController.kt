package com.github.order.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.order.entity.OrderReturnReason
import com.github.order.service.OrderReturnReasonService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:18:14
 */
@RestController
@RequestMapping("order/orderreturnreason")
@Api
class OrderReturnReasonController {

    @Autowired
    lateinit var orderReturnReasonService: OrderReturnReasonService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val orderReturnReason = orderReturnReasonService.getById(id)
        return resultSuccess().put("orderReturnReason", orderReturnReason)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody orderReturnReason: OrderReturnReason): ResultDto {
        orderReturnReasonService.saveOrUpdate(orderReturnReason)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody orderReturnReason: OrderReturnReason): ResultDto {
        orderReturnReasonService.saveOrUpdate(orderReturnReason)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        orderReturnReasonService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val orderReturnReasons = orderReturnReasonService.getAll()
        return resultSuccess().put("orderReturnReason", orderReturnReasons.toList())
    }
}
