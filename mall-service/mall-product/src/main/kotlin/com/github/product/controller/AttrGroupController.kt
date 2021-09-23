package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.AttrGroup
import com.github.product.service.AttrGroupService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@RestController
@RequestMapping("product/attrgroup")
@Api
class AttrGroupController {

    @Autowired
    lateinit var attrGroupService: AttrGroupService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val attrGroup = attrGroupService.getById(id)
        return resultSuccess().put("attrGroup", attrGroup)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody attrGroup: AttrGroup): ResultDto {
        attrGroupService.saveOrUpdate(attrGroup)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody attrGroup: AttrGroup): ResultDto {
        attrGroupService.saveOrUpdate(attrGroup)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        attrGroupService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val attrGroups = attrGroupService.getAll()
        return resultSuccess().put("attrGroup", attrGroups.toList())
    }
}
