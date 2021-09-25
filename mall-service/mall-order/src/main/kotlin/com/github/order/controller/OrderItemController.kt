package com.github.order.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.order.entity.OrderItem
import com.github.order.service.OrderItemService
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
@RequestMapping("order/orderitem")
@Api
class OrderItemController {

    @Autowired
    lateinit var orderItemService: OrderItemService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val orderItem = orderItemService.getById(id)
        return resultSuccess().put("orderItem", orderItem)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody orderItem: OrderItem): ResultDto {
        orderItemService.saveOrUpdate(orderItem)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody orderItem: OrderItem): ResultDto {
        orderItemService.saveOrUpdate(orderItem)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        orderItemService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val orderItems = orderItemService.getAll()
        return resultSuccess().put("orderItem", orderItems.toList())
    }
}
