package com.github.member.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.member.entity.MemberCollectSpu
import com.github.member.service.MemberCollectSpuService
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
@RequestMapping("member/membercollectspu")
@Api
class MemberCollectSpuController {

    @Autowired
    lateinit var memberCollectSpuService: MemberCollectSpuService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val memberCollectSpu = memberCollectSpuService.getById(id)
        return resultSuccess().put("memberCollectSpu", memberCollectSpu)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody memberCollectSpu: MemberCollectSpu): ResultDto {
        memberCollectSpuService.saveOrUpdate(memberCollectSpu)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody memberCollectSpu: MemberCollectSpu): ResultDto {
        memberCollectSpuService.saveOrUpdate(memberCollectSpu)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        memberCollectSpuService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val memberCollectSpus = memberCollectSpuService.getAll()
        return resultSuccess().put("memberCollectSpu", memberCollectSpus.toList())
    }
}
