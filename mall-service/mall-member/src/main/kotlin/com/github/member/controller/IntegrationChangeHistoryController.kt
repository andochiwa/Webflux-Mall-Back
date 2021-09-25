package com.github.member.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.member.entity.IntegrationChangeHistory
import com.github.member.service.IntegrationChangeHistoryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:43:36
 */
@RestController
@RequestMapping("member/integrationchangehistory")
@Api
class IntegrationChangeHistoryController {

    @Autowired
    lateinit var integrationChangeHistoryService: IntegrationChangeHistoryService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val integrationChangeHistory = integrationChangeHistoryService.getById(id)
        return resultSuccess().put("integrationChangeHistory", integrationChangeHistory)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody integrationChangeHistory: IntegrationChangeHistory): ResultDto {
        integrationChangeHistoryService.saveOrUpdate(integrationChangeHistory)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody integrationChangeHistory: IntegrationChangeHistory): ResultDto {
        integrationChangeHistoryService.saveOrUpdate(integrationChangeHistory)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        integrationChangeHistoryService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val integrationChangeHistorys = integrationChangeHistoryService.getAll()
        return resultSuccess().put("integrationChangeHistory", integrationChangeHistorys.toList())
    }
}
