package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.WmsWareSku
import com.github.ware.service.WmsWareSkuService
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
@RequestMapping("ware/wmswaresku")
@Api
class WmsWareSkuController {

    @Autowired
    lateinit var wmsWareSkuService: WmsWareSkuService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wmsWareSku = wmsWareSkuService.getById(id)
        return resultSuccess().put("wmsWareSku", wmsWareSku)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody wmsWareSku: WmsWareSku): ResultDto {
        wmsWareSkuService.saveOrUpdate(wmsWareSku)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody wmsWareSku: WmsWareSku): ResultDto {
        wmsWareSkuService.saveOrUpdate(wmsWareSku)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        wmsWareSkuService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wmsWareSkus = wmsWareSkuService.getAll()
        return resultSuccess().put("wmsWareSku", wmsWareSkus.toList())
    }
}
