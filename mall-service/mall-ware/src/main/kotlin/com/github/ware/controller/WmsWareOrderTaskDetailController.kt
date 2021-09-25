package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.WmsWareOrderTaskDetail
import com.github.ware.service.WmsWareOrderTaskDetailService
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
@RequestMapping("ware/wmswareordertaskdetail")
@Api
class WmsWareOrderTaskDetailController {

    @Autowired
    lateinit var wmsWareOrderTaskDetailService: WmsWareOrderTaskDetailService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wmsWareOrderTaskDetail = wmsWareOrderTaskDetailService.getById(id)
        return resultSuccess().put("wmsWareOrderTaskDetail", wmsWareOrderTaskDetail)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody wmsWareOrderTaskDetail: WmsWareOrderTaskDetail): ResultDto {
        wmsWareOrderTaskDetailService.saveOrUpdate(wmsWareOrderTaskDetail)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody wmsWareOrderTaskDetail: WmsWareOrderTaskDetail): ResultDto {
        wmsWareOrderTaskDetailService.saveOrUpdate(wmsWareOrderTaskDetail)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        wmsWareOrderTaskDetailService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wmsWareOrderTaskDetails = wmsWareOrderTaskDetailService.getAll()
        return resultSuccess().put("wmsWareOrderTaskDetail", wmsWareOrderTaskDetails.toList())
    }
}
