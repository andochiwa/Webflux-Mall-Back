package com.github.member.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.member.entity.MemberReceiveAddress
import com.github.member.service.MemberReceiveAddressService
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
@RequestMapping("member/memberreceiveaddress")
@Api
class MemberReceiveAddressController {

    @Autowired
    lateinit var memberReceiveAddressService: MemberReceiveAddressService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val memberReceiveAddress = memberReceiveAddressService.getById(id)
        return resultSuccess().put("memberReceiveAddress", memberReceiveAddress)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody memberReceiveAddress: MemberReceiveAddress): ResultDto {
        memberReceiveAddressService.saveOrUpdate(memberReceiveAddress)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody memberReceiveAddress: MemberReceiveAddress): ResultDto {
        memberReceiveAddressService.saveOrUpdate(memberReceiveAddress)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        memberReceiveAddressService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val memberReceiveAddresss = memberReceiveAddressService.getAll()
        return resultSuccess().put("memberReceiveAddress", memberReceiveAddresss.toList())
    }
}
