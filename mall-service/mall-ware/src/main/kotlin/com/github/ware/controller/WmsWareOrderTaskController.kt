package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.WmsWareOrderTask
import com.github.ware.service.WmsWareOrderTaskService
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
@RequestMapping("ware/wmswareordertask")
@Api
class WmsWareOrderTaskController {

    @Autowired
    lateinit var wmsWareOrderTaskService: WmsWareOrderTaskService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wmsWareOrderTask = wmsWareOrderTaskService.getById(id)
        return resultSuccess().put("wmsWareOrderTask", wmsWareOrderTask)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody wmsWareOrderTask: WmsWareOrderTask): ResultDto {
        wmsWareOrderTaskService.saveOrUpdate(wmsWareOrderTask)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody wmsWareOrderTask: WmsWareOrderTask): ResultDto {
        wmsWareOrderTaskService.saveOrUpdate(wmsWareOrderTask)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        wmsWareOrderTaskService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wmsWareOrderTasks = wmsWareOrderTaskService.getAll()
        return resultSuccess().put("wmsWareOrderTask", wmsWareOrderTasks.toList())
    }
}
