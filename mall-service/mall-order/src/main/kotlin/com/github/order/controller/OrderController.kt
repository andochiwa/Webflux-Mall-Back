package com.github.order.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.order.entity.Order
import com.github.order.service.OrderService
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
@RequestMapping("order/order")
@Api
class OrderController {

    @Autowired
    lateinit var orderService: OrderService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val order = orderService.getById(id)
        return resultSuccess().put("order", order)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody order: Order): ResultDto {
        orderService.saveOrUpdate(order)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody order: Order): ResultDto {
        orderService.saveOrUpdate(order)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        orderService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val orders = orderService.getAll()
        return resultSuccess().put("order", orders.toList())
    }
}
