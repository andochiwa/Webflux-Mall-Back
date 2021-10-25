package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.SpuInfo
import com.github.product.service.SpuInfoService
import com.github.product.vo.SpuSaveVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@RestController
@RequestMapping("product/spuinfo")
@Api
class SpuInfoController {

    @Autowired
    lateinit var spuInfoService: SpuInfoService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val spuInfo = spuInfoService.getById(id)
        return resultSuccess().put("spuInfo", spuInfo)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody spuSaveVo: SpuSaveVo): ResultDto {
        spuInfoService.saveSpu(spuSaveVo)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody spuInfo: SpuInfo): ResultDto {
        spuInfoService.saveOrUpdate(spuInfo)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        spuInfoService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val spuInfos = spuInfoService.getAll()
        return resultSuccess().put("spuInfo", spuInfos.toList())
    }


    @GetMapping("pagination")
    @ApiOperation("get list on conditions")
    suspend fun geyListOnConditions(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("sortField", required = false) sortField: String?,
        @RequestParam("order", required = false) order: String?,
        @RequestParam("key", required = false) key: String?,
        @RequestParam("catelogId", required = false) catelogId: Long?,
        @RequestParam("brandId", required = false) brandId: Long?,
        @RequestParam("status", required = false) status: Int?,
    ): ResultDto {
        val map =
            spuInfoService.geyListOnConditions(page - 1, limit, sortField, order, key, catelogId, brandId, status)
        return resultSuccess().putAll(map)
    }

    @PutMapping("{spuId}/put-on")
    @ApiIgnore("put on sale")
    suspend fun putOnSale(@PathVariable("spuId") id: Long): ResultDto {
        spuInfoService.putOnSale(id)
        return resultSuccess()
    }
}
