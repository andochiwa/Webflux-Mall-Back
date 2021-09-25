package com.github.member.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.member.entity.Member
import com.github.member.service.MemberService
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
@RequestMapping("member/member")
@Api
class MemberController {

    @Autowired
    lateinit var memberService: MemberService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val member = memberService.getById(id)
        return resultSuccess().put("member", member)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody member: Member): ResultDto {
        memberService.saveOrUpdate(member)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody member: Member): ResultDto {
        memberService.saveOrUpdate(member)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        memberService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val members = memberService.getAll()
        return resultSuccess().put("member", members.toList())
    }
}
