package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.WmsPurchase
import com.github.ware.service.WmsPurchaseService
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
@RequestMapping("ware/wmspurchase")
@Api
class WmsPurchaseController {

    @Autowired
    lateinit var wmsPurchaseService: WmsPurchaseService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wmsPurchase = wmsPurchaseService.getById(id)
        return resultSuccess().put("wmsPurchase", wmsPurchase)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody wmsPurchase: WmsPurchase): ResultDto {
        wmsPurchaseService.saveOrUpdate(wmsPurchase)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody wmsPurchase: WmsPurchase): ResultDto {
        wmsPurchaseService.saveOrUpdate(wmsPurchase)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        wmsPurchaseService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wmsPurchases = wmsPurchaseService.getAll()
        return resultSuccess().put("wmsPurchase", wmsPurchases.toList())
    }
}
