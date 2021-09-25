package com.github.member.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.member.entity.GrowthChangeHistory
import com.github.member.service.GrowthChangeHistoryService
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
@RequestMapping("member/growthchangehistory")
@Api
class GrowthChangeHistoryController {

    @Autowired
    lateinit var growthChangeHistoryService: GrowthChangeHistoryService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val growthChangeHistory = growthChangeHistoryService.getById(id)
        return resultSuccess().put("growthChangeHistory", growthChangeHistory)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody growthChangeHistory: GrowthChangeHistory): ResultDto {
        growthChangeHistoryService.saveOrUpdate(growthChangeHistory)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody growthChangeHistory: GrowthChangeHistory): ResultDto {
        growthChangeHistoryService.saveOrUpdate(growthChangeHistory)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        growthChangeHistoryService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val growthChangeHistorys = growthChangeHistoryService.getAll()
        return resultSuccess().put("growthChangeHistory", growthChangeHistorys.toList())
    }
}
