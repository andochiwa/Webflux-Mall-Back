package com.github.member.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.member.entity.MemberCollectSubject
import com.github.member.service.MemberCollectSubjectService
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
@RequestMapping("member/membercollectsubject")
@Api
class MemberCollectSubjectController {

    @Autowired
    lateinit var memberCollectSubjectService: MemberCollectSubjectService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val memberCollectSubject = memberCollectSubjectService.getById(id)
        return resultSuccess().put("memberCollectSubject", memberCollectSubject)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody memberCollectSubject: MemberCollectSubject): ResultDto {
        memberCollectSubjectService.saveOrUpdate(memberCollectSubject)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody memberCollectSubject: MemberCollectSubject): ResultDto {
        memberCollectSubjectService.saveOrUpdate(memberCollectSubject)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        memberCollectSubjectService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val memberCollectSubjects = memberCollectSubjectService.getAll()
        return resultSuccess().put("memberCollectSubject", memberCollectSubjects.toList())
    }
}
