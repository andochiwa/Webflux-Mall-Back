package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.Purchase
import com.github.ware.service.PurchaseService
import com.github.ware.vo.MergeVo
import com.github.ware.vo.PurchaseDoneVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:23
 */
@RestController
@RequestMapping("ware/purchase")
@Api
class PurchaseController {

    @Autowired
    lateinit var purchaseService: PurchaseService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val purchase = purchaseService.getById(id)
        return resultSuccess().put("purchase", purchase)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody purchase: Purchase): ResultDto {
        purchaseService.saveOrUpdate(purchase)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody purchase: Purchase): ResultDto {
        purchaseService.saveOrUpdate(purchase)
        return resultSuccess()
    }

    @DeleteMapping
    @ApiOperation("deleteByIds")
    suspend fun deleteById(@RequestBody ids: List<Long>): ResultDto {
        purchaseService.deleteByIds(ids)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val purchases = purchaseService.getAll()
        return resultSuccess().put("purchase", purchases.toList())
    }

    @GetMapping("pagination")
    @ApiOperation("get list on conditions")
    suspend fun getOnConditions(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("status", required = false) status: Int?,
        @RequestParam("key", required = false) key: String?
    ): ResultDto {
        val map = purchaseService.getOnConditions(page - 1, limit, status, key)
        return resultSuccess().putAll(map)
    }

    @GetMapping("unreceived")
    @ApiOperation("get unreceived(status == 0, 1) list")
    suspend fun getUnReceiveList(): ResultDto {
        val purchase = purchaseService.getUnreceivedList()
        return resultSuccess().put("list", purchase)
    }

    @PostMapping("merge")
    @ApiOperation("merge purchase and purchase detail")
    suspend fun merge(@RequestBody mergeVo: MergeVo): ResultDto {
        purchaseService.merge(mergeVo)
        return resultSuccess()
    }

    @PostMapping("received")
    @ApiOperation("received purchase")
    suspend fun receivedPurchase(@RequestBody purchaseIds: List<Long>): ResultDto {
        purchaseService.receivedPurchase(purchaseIds)
        return resultSuccess()
    }

    @PostMapping("done")
    @ApiOperation("complete purchase")
    suspend fun completePurchase(@Validated @RequestBody purchaseDoneVo: PurchaseDoneVo): ResultDto {
        purchaseService.completePurchase(purchaseDoneVo)
        return resultSuccess()
    }
}
