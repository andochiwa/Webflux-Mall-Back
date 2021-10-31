package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.vaild.AddGroup
import com.github.vaild.UpdateGroup
import com.github.ware.entity.WareOrderTask
import com.github.ware.service.WareOrderTaskService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:23
 */
@RestController
@RequestMapping("ware/wareordertask")
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
    suspend fun insert(@Validated(AddGroup::class) @RequestBody wareOrderTask: WareOrderTask): ResultDto {
        wareOrderTaskService.saveOrUpdate(wareOrderTask)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@Validated(UpdateGroup::class) @RequestBody wareOrderTask: WareOrderTask): ResultDto {
        wareOrderTaskService.saveOrUpdate(wareOrderTask)
        return resultSuccess()
    }

    @DeleteMapping
    @ApiOperation("deleteByIds")
    suspend fun deleteById(@RequestBody ids: List<Long>): ResultDto {
        wareOrderTaskService.deleteByIds(ids)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wareOrderTasks = wareOrderTaskService.getAll()
        return resultSuccess().put("wareOrderTask", wareOrderTasks.toList())
    }

    @GetMapping("pagination")
    @ApiOperation("get pagination")
    suspend fun getPagination(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("key", required = false) key: String?
    ): ResultDto {
        val map = wareOrderTaskService.getPagination(page - 1, limit, key)
        return resultSuccess().putAll(map)
    }
}
