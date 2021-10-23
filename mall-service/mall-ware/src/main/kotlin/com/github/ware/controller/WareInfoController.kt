package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.WareInfo
import com.github.ware.service.WareInfoService
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
@RequestMapping("ware/wareinfo")
@Api
class WareInfoController {

    @Autowired
    lateinit var wareInfoService: WareInfoService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wmsWareInfo = wareInfoService.getById(id)
        return resultSuccess().put("wmsWareInfo", wmsWareInfo)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody wareInfo: WareInfo): ResultDto {
        wareInfoService.saveOrUpdate(wareInfo)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody wareInfo: WareInfo): ResultDto {
        wareInfoService.saveOrUpdate(wareInfo)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        wareInfoService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wmsWareInfos = wareInfoService.getAll()
        return resultSuccess().put("wmsWareInfo", wmsWareInfos.toList())
    }

    @GetMapping("pagination")
    @ApiOperation("get pagination")
    suspend fun getPagination(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("key") key: String?
    ): ResultDto {
        val map = wareInfoService.getPagination(page - 1, limit, key)
        return resultSuccess().putAll(map)
    }
}
