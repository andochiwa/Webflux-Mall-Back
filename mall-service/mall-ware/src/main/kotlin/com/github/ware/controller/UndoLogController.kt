package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.ware.entity.UndoLog
import com.github.ware.service.UndoLogService
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
@RequestMapping("ware/undolog")
@Api
class UndoLogController {

    @Autowired
    lateinit var undoLogService: UndoLogService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val undoLog = undoLogService.getById(id)
        return resultSuccess().put("undoLog", undoLog)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody undoLog: UndoLog): ResultDto {
        undoLogService.saveOrUpdate(undoLog)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody undoLog: UndoLog): ResultDto {
        undoLogService.saveOrUpdate(undoLog)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        undoLogService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val undoLogs = undoLogService.getAll()
        return resultSuccess().put("undoLog", undoLogs.toList())
    }
}
