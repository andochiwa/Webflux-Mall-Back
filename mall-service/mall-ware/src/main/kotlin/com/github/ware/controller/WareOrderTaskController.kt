package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.WareOrderTask
import com.github.ware.service.WareOrderTaskService
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
@RequestMapping("wareswareordertask")
@Api
class WareOrderTaskController {

    @Autowired
    lateinit var wareOrderTaskService: WareOrderTaskService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wareOrderTask = wareOrderTaskService.getById(id)
        return resultSuccess().put("wareOrderTask", wareOrderTask)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody wareOrderTask: WareOrderTask): ResultDto {
        wareOrderTaskService.saveOrUpdate(wareOrderTask)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody wareOrderTask: WareOrderTask): ResultDto {
        wareOrderTaskService.saveOrUpdate(wareOrderTask)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        wareOrderTaskService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wareOrderTasks = wareOrderTaskService.getAll()
        return resultSuccess().put("wareOrderTask", wareOrderTasks.toList())
    }
}
