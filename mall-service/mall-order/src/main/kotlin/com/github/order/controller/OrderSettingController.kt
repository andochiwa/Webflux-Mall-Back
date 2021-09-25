package com.github.order.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.order.entity.OrderSetting
import com.github.order.service.OrderSettingService
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
@RequestMapping("order/ordersetting")
@Api
class OrderSettingController {

    @Autowired
    lateinit var orderSettingService: OrderSettingService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val orderSetting = orderSettingService.getById(id)
        return resultSuccess().put("orderSetting", orderSetting)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody orderSetting: OrderSetting): ResultDto {
        orderSettingService.saveOrUpdate(orderSetting)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody orderSetting: OrderSetting): ResultDto {
        orderSettingService.saveOrUpdate(orderSetting)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        orderSettingService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val orderSettings = orderSettingService.getAll()
        return resultSuccess().put("orderSetting", orderSettings.toList())
    }
}
