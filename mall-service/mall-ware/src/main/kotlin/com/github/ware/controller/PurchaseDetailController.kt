package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.PurchaseDetail
import com.github.ware.service.PurchaseDetailService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:24
 */
@RestController
@RequestMapping("ware/purchasedetail")
@Api
class PurchaseDetailController {

    @Autowired
    lateinit var purchaseDetailService: PurchaseDetailService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wmsPurchaseDetail = purchaseDetailService.getById(id)
        return resultSuccess().put("wmsPurchaseDetail", wmsPurchaseDetail)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody purchaseDetail: PurchaseDetail): ResultDto {
        purchaseDetailService.saveOrUpdate(purchaseDetail)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody purchaseDetail: PurchaseDetail): ResultDto {
        purchaseDetailService.saveOrUpdate(purchaseDetail)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        purchaseDetailService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wmsPurchaseDetails = purchaseDetailService.getAll()
        return resultSuccess().put("wmsPurchaseDetail", wmsPurchaseDetails.toList())
    }
}
