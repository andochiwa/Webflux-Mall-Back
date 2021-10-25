package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.WareOrderTaskDetail
import com.github.ware.service.WareOrderTaskDetailService
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
@RequestMapping("ware/wareordertaskdetail")
@Api
class WareOrderTaskDetailController {

    @Autowired
    lateinit var wareOrderTaskDetailService: WareOrderTaskDetailService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wareOrderTaskDetail = wareOrderTaskDetailService.getById(id)
        return resultSuccess().put("wareOrderTaskDetail", wareOrderTaskDetail)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody wareOrderTaskDetail: WareOrderTaskDetail): ResultDto {
        wareOrderTaskDetailService.saveOrUpdate(wareOrderTaskDetail)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody wareOrderTaskDetail: WareOrderTaskDetail): ResultDto {
        wareOrderTaskDetailService.saveOrUpdate(wareOrderTaskDetail)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        wareOrderTaskDetailService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wareOrderTaskDetails = wareOrderTaskDetailService.getAll()
        return resultSuccess().put("wareOrderTaskDetail", wareOrderTaskDetails.toList())
    }
}
