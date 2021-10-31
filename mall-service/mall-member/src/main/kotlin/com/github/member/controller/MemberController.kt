package com.github.member.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.member.entity.Member
import com.github.member.service.MemberService
import com.github.vaild.AddGroup
import com.github.vaild.UpdateGroup
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
    suspend fun insert(@Validated(AddGroup::class) @RequestBody member: Member): ResultDto {
        memberService.saveOrUpdate(member)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@Validated(UpdateGroup::class) @RequestBody member: Member): ResultDto {
        memberService.saveOrUpdate(member)
        return resultSuccess()
    }

    @DeleteMapping
    @ApiOperation("deleteByIds")
    suspend fun deleteById(@RequestBody ids: List<Long>): ResultDto {
        memberService.deleteByIds(ids)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val members = memberService.getAll()
        return resultSuccess().put("member", members.toList())
    }

    @GetMapping("pagination")
    @ApiOperation("get pagination")
    suspend fun getPagination(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("key", required = false) key: String?
    ): ResultDto {
        val map = memberService.getPagination(page - 1, limit, key)
        return resultSuccess().putAll(map)
    }
}
