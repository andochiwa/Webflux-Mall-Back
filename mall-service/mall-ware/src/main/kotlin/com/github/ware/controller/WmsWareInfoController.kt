package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.WmsWareInfo
import com.github.ware.service.WmsWareInfoService
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
@RequestMapping("ware/wmswareinfo")
@Api
class WmsWareInfoController {

    @Autowired
    lateinit var wmsWareInfoService: WmsWareInfoService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wmsWareInfo = wmsWareInfoService.getById(id)
        return resultSuccess().put("wmsWareInfo", wmsWareInfo)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody wmsWareInfo: WmsWareInfo): ResultDto {
        wmsWareInfoService.saveOrUpdate(wmsWareInfo)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody wmsWareInfo: WmsWareInfo): ResultDto {
        wmsWareInfoService.saveOrUpdate(wmsWareInfo)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        wmsWareInfoService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wmsWareInfos = wmsWareInfoService.getAll()
        return resultSuccess().put("wmsWareInfo", wmsWareInfos.toList())
    }
}
