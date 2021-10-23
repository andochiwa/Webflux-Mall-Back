package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.SkuInfo
import com.github.product.service.SkuInfoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:43:25
 */
@RestController
@RequestMapping("product/skuinfo")
@Api
class SkuInfoController {

    @Autowired
    lateinit var skuInfoService: SkuInfoService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val skuInfo = skuInfoService.getById(id)
        return resultSuccess().put("skuInfo", skuInfo)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody skuInfo: SkuInfo): ResultDto {
        skuInfoService.saveOrUpdate(skuInfo)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody skuInfo: SkuInfo): ResultDto {
        skuInfoService.saveOrUpdate(skuInfo)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        skuInfoService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val skuInfos = skuInfoService.getAll()
        return resultSuccess().put("skuInfo", skuInfos.toList())
    }

    @GetMapping("pagination")
    @ApiOperation("get sku list on conditions")
    suspend fun getSkusOnConditions(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("key", required = false) key: String?,
        @RequestParam("catelogId", required = false) catelogId: Long?,
        @RequestParam("brandId", required = false) brandId: Long?,
        @RequestParam("min", required = false) min: Int?,
        @RequestParam("max", required = false) max: Int?,
    ): ResultDto {
        val map = skuInfoService.getSkusOnConditions(page - 1, limit, key, catelogId, brandId, min, max)
        return resultSuccess().putAll(map)
    }
}
