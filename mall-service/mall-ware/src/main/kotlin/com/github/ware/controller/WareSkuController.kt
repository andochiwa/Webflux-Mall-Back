package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.WareSku
import com.github.ware.service.WareSkuService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
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
    suspend fun insert(@RequestBody wareSku: WareSku): ResultDto {
        wareSkuService.saveOrUpdate(wareSku)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody wareSku: WareSku): ResultDto {
        wareSkuService.saveOrUpdate(wareSku)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        wareSkuService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wareSkus = wareSkuService.getAll()
        return resultSuccess().put("wareSku", wareSkus.toList())
    }
}
