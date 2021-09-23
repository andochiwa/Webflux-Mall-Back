package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.CommentReplay
import com.github.product.service.CommentReplayService
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
@RequestMapping("product/commentreplay")
@Api
class CommentReplayController {

    @Autowired
    lateinit var commentReplayService: CommentReplayService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val commentReplay = commentReplayService.getById(id)
        return resultSuccess().put("commentReplay", commentReplay)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody commentReplay: CommentReplay): ResultDto {
        commentReplayService.saveOrUpdate(commentReplay)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody commentReplay: CommentReplay): ResultDto {
        commentReplayService.saveOrUpdate(commentReplay)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        commentReplayService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val commentReplays = commentReplayService.getAll()
        return resultSuccess().put("commentReplay", commentReplays.toList())
    }
}
