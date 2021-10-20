package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.Attr
import com.github.product.service.AttrService
import com.github.product.vo.AttrVo
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
 * @date 2021-09-24 00:47:19
 */
@RestController
@RequestMapping("product/attr")
@Api
class AttrController {

    @Autowired
    lateinit var attrService: AttrService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val attr = attrService.getById(id)
        return resultSuccess().put("attr", attr)
    }

    @GetMapping("{attrType}/list/{catelogId}")
    @ApiOperation("get base attr list")
    suspend fun getBaseAttrList(
        @PathVariable("attrType") attrType: Int,
        @PathVariable("catelogId") catelogId: Long,
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("key", required = false) key: String?
    ): ResultDto {
        val attrMap = attrService.getBaseAttrList(attrType, catelogId, page - 1, limit, key)
        return resultSuccess().putAll(attrMap)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@Validated(AddGroup::class) @RequestBody attrVo: AttrVo): ResultDto {
        attrService.save(attrVo)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@Validated(UpdateGroup::class) @RequestBody attr: Attr): ResultDto {
        attrService.saveOrUpdate(attr)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        attrService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val attrs = attrService.getAll()
        return resultSuccess().put("attr", attrs.toList())
    }
}
