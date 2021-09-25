package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.WmsPurchaseDetail
import com.github.ware.service.WmsPurchaseDetailService
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
@RequestMapping("ware/wmspurchasedetail")
@Api
class WmsPurchaseDetailController {

    @Autowired
    lateinit var wmsPurchaseDetailService: WmsPurchaseDetailService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wmsPurchaseDetail = wmsPurchaseDetailService.getById(id)
        return resultSuccess().put("wmsPurchaseDetail", wmsPurchaseDetail)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody wmsPurchaseDetail: WmsPurchaseDetail): ResultDto {
        wmsPurchaseDetailService.saveOrUpdate(wmsPurchaseDetail)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody wmsPurchaseDetail: WmsPurchaseDetail): ResultDto {
        wmsPurchaseDetailService.saveOrUpdate(wmsPurchaseDetail)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        wmsPurchaseDetailService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wmsPurchaseDetails = wmsPurchaseDetailService.getAll()
        return resultSuccess().put("wmsPurchaseDetail", wmsPurchaseDetails.toList())
    }
}
