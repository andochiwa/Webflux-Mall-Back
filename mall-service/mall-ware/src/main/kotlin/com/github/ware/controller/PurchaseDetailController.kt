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
        val purchaseDetail = purchaseDetailService.getById(id)
        return resultSuccess().put("purchaseDetail", purchaseDetail)
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

    @DeleteMapping
    @ApiOperation("deleteByIds")
    suspend fun deleteById(@RequestBody ids: List<Long>): ResultDto {
        purchaseDetailService.deleteByIds(ids)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val purchaseDetails = purchaseDetailService.getAll()
        return resultSuccess().put("purchaseDetail", purchaseDetails.toList())
    }

    @GetMapping("pagination")
    @ApiOperation("get list on conditions")
    suspend fun getOnConditions(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("wareId", required = false) wareId: Long?,
        @RequestParam("status", required = false) status: Int?
    ): ResultDto {
        val map = purchaseDetailService.getOnConditions(page - 1, limit, wareId, status)
        return resultSuccess().putAll(map)
    }
}
