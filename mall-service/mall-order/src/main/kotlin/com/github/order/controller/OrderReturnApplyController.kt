package com.github.order.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.order.entity.OrderReturnApply
import com.github.order.service.OrderReturnApplyService
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
@RequestMapping("order/orderreturnapply")
@Api
class OrderReturnApplyController {

    @Autowired
    lateinit var orderReturnApplyService: OrderReturnApplyService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val orderReturnApply = orderReturnApplyService.getById(id)
        return resultSuccess().put("orderReturnApply", orderReturnApply)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody orderReturnApply: OrderReturnApply): ResultDto {
        orderReturnApplyService.saveOrUpdate(orderReturnApply)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody orderReturnApply: OrderReturnApply): ResultDto {
        orderReturnApplyService.saveOrUpdate(orderReturnApply)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        orderReturnApplyService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val orderReturnApplys = orderReturnApplyService.getAll()
        return resultSuccess().put("orderReturnApply", orderReturnApplys.toList())
    }
}
