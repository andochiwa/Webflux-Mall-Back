package com.github.member.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.member.entity.MemberStatisticsInfo
import com.github.member.service.MemberStatisticsInfoService
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
@RequestMapping("member/memberstatisticsinfo")
@Api
class MemberStatisticsInfoController {

    @Autowired
    lateinit var memberStatisticsInfoService: MemberStatisticsInfoService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val memberStatisticsInfo = memberStatisticsInfoService.getById(id)
        return resultSuccess().put("memberStatisticsInfo", memberStatisticsInfo)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody memberStatisticsInfo: MemberStatisticsInfo): ResultDto {
        memberStatisticsInfoService.saveOrUpdate(memberStatisticsInfo)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody memberStatisticsInfo: MemberStatisticsInfo): ResultDto {
        memberStatisticsInfoService.saveOrUpdate(memberStatisticsInfo)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        memberStatisticsInfoService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val memberStatisticsInfos = memberStatisticsInfoService.getAll()
        return resultSuccess().put("memberStatisticsInfo", memberStatisticsInfos.toList())
    }
}
