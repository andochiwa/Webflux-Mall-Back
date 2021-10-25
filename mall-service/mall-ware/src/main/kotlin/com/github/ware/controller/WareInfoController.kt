package com.github.ware.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.vaild.AddGroup
import com.github.vaild.UpdateGroup
import com.github.ware.entity.WareInfo
import com.github.ware.service.WareInfoService
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
 * @date 2021-09-26 03:53:24
 */
@RestController
@RequestMapping("ware/wareinfo")
@Api
class WareInfoController {

    @Autowired
    lateinit var wareInfoService: WareInfoService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val wareInfo = wareInfoService.getById(id)
        return resultSuccess().put("wareInfo", wareInfo)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@Validated(AddGroup::class) @RequestBody wareInfo: WareInfo): ResultDto {
        wareInfoService.saveOrUpdate(wareInfo)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@Validated(UpdateGroup::class) @RequestBody wareInfo: WareInfo): ResultDto {
        wareInfoService.saveOrUpdate(wareInfo)
        return resultSuccess()
    }

    @DeleteMapping
    @ApiOperation("deleteByIds")
    suspend fun deleteById(@RequestBody ids: List<Long>): ResultDto {
        wareInfoService.deleteById(ids)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val wareInfos = wareInfoService.getAll()
        return resultSuccess().put("wareInfo", wareInfos.toList())
    }

    @GetMapping("pagination")
    @ApiOperation("get pagination")
    suspend fun getPagination(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("key") key: String?
    ): ResultDto {
        val map = wareInfoService.getPagination(page - 1, limit, key)
        return resultSuccess().putAll(map)
    }
}
