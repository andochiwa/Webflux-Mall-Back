package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.SpuInfo
import com.github.product.service.SpuInfoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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
    suspend fun insert(@RequestBody spuInfo: SpuInfo): ResultDto {
        spuInfoService.saveOrUpdate(spuInfo)
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
}
