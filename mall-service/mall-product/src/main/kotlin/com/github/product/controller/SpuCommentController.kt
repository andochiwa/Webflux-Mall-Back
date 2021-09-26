package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.SpuComment
import com.github.product.service.SpuCommentService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:43:25
 */
@RestController
@RequestMapping("product/spucomment")
@Api
class SpuCommentController {

    @Autowired
    lateinit var spuCommentService: SpuCommentService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val spuComment = spuCommentService.getById(id)
        return resultSuccess().put("spuComment", spuComment)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody spuComment: SpuComment): ResultDto {
        spuCommentService.saveOrUpdate(spuComment)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody spuComment: SpuComment): ResultDto {
        spuCommentService.saveOrUpdate(spuComment)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        spuCommentService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val spuComments = spuCommentService.getAll()
        return resultSuccess().put("spuComment", spuComments.toList())
    }
}
