package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.vaild.AddGroup
import com.github.vaild.UpdateGroup
import com.github.ware.entity.WareSku
import com.github.ware.service.WareSkuService
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
@RequestMapping("ware/waresku")
@Api
class WareSkuController {

    @Autowired
    lateinit var wareSkuService: WareSkuService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wareSku = wareSkuService.getById(id)
        return resultSuccess().put("wareSku", wareSku)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@Validated(AddGroup::class) @RequestBody wareSku: WareSku): ResultDto {
        wareSkuService.saveOrUpdate(wareSku)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@Validated(UpdateGroup::class) @RequestBody wareSku: WareSku): ResultDto {
        wareSkuService.saveOrUpdate(wareSku)
        return resultSuccess()
    }

    @DeleteMapping
    @ApiOperation("deleteByIds")
    suspend fun deleteById(@RequestBody ids: List<Long>): ResultDto {
        wareSkuService.deleteByIds(ids)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wareSkus = wareSkuService.getAll()
        return resultSuccess().put("wareSku", wareSkus.toList())
    }

    @GetMapping("pagination")
    @ApiOperation("get list pagination")
    suspend fun getPagination(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("skuId") skuId: Long?,
        @RequestParam("wareId") wareId: Long?
    ): ResultDto {
        val map = wareSkuService.getPagination(page - 1, limit, skuId, wareId)
        return resultSuccess().putAll(map)
    }
}
