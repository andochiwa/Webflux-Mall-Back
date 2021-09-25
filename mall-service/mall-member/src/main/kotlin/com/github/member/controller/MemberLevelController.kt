package com.github.member.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.member.entity.MemberLevel
import com.github.member.service.MemberLevelService
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
@RequestMapping("member/memberlevel")
@Api
class MemberLevelController {

    @Autowired
    lateinit var memberLevelService: MemberLevelService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val memberLevel = memberLevelService.getById(id)
        return resultSuccess().put("memberLevel", memberLevel)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody memberLevel: MemberLevel): ResultDto {
        memberLevelService.saveOrUpdate(memberLevel)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody memberLevel: MemberLevel): ResultDto {
        memberLevelService.saveOrUpdate(memberLevel)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        memberLevelService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val memberLevels = memberLevelService.getAll()
        return resultSuccess().put("memberLevel", memberLevels.toList())
    }
}
