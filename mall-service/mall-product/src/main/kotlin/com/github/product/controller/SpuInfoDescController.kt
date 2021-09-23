package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.SpuInfoDesc
import com.github.product.service.SpuInfoDescService
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
@RequestMapping("product/spuinfodesc")
@Api
class SpuInfoDescController {

    @Autowired
    lateinit var spuInfoDescService: SpuInfoDescService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val spuInfoDesc = spuInfoDescService.getById(id)
        return resultSuccess().put("spuInfoDesc", spuInfoDesc)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody spuInfoDesc: SpuInfoDesc): ResultDto {
        spuInfoDescService.saveOrUpdate(spuInfoDesc)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody spuInfoDesc: SpuInfoDesc): ResultDto {
        spuInfoDescService.saveOrUpdate(spuInfoDesc)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        spuInfoDescService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val spuInfoDescs = spuInfoDescService.getAll()
        return resultSuccess().put("spuInfoDesc", spuInfoDescs.toList())
    }
}
