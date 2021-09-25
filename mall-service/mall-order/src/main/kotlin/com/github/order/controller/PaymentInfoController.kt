package com.github.order.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.order.entity.PaymentInfo
import com.github.order.service.PaymentInfoService
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
@RequestMapping("order/paymentinfo")
@Api
class PaymentInfoController {

    @Autowired
    lateinit var paymentInfoService: PaymentInfoService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val paymentInfo = paymentInfoService.getById(id)
        return resultSuccess().put("paymentInfo", paymentInfo)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody paymentInfo: PaymentInfo): ResultDto {
        paymentInfoService.saveOrUpdate(paymentInfo)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody paymentInfo: PaymentInfo): ResultDto {
        paymentInfoService.saveOrUpdate(paymentInfo)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        paymentInfoService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val paymentInfos = paymentInfoService.getAll()
        return resultSuccess().put("paymentInfo", paymentInfos.toList())
    }
}
