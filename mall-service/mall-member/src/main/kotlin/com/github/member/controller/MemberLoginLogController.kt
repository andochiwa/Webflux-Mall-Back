package com.github.member.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.member.entity.MemberLoginLog
import com.github.member.service.MemberLoginLogService
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
@RequestMapping("member/memberloginlog")
@Api
class MemberLoginLogController {

    @Autowired
    lateinit var memberLoginLogService: MemberLoginLogService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val memberLoginLog = memberLoginLogService.getById(id)
        return resultSuccess().put("memberLoginLog", memberLoginLog)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody memberLoginLog: MemberLoginLog): ResultDto {
        memberLoginLogService.saveOrUpdate(memberLoginLog)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody memberLoginLog: MemberLoginLog): ResultDto {
        memberLoginLogService.saveOrUpdate(memberLoginLog)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        memberLoginLogService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val memberLoginLogs = memberLoginLogService.getAll()
        return resultSuccess().put("memberLoginLog", memberLoginLogs.toList())
    }
}
