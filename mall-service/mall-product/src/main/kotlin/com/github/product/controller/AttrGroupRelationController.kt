package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.AttrGroupRelation
import com.github.product.service.AttrGroupRelationService
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
@RequestMapping("product/attrgrouprelation")
@Api
class AttrGroupRelationController {

    @Autowired
    lateinit var attrGroupRelationService: AttrGroupRelationService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val attrGroupRelation = attrGroupRelationService.getById(id)
        return resultSuccess().put("attrGroupRelation", attrGroupRelation)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody attrGroupRelation: AttrGroupRelation): ResultDto {
        attrGroupRelationService.saveOrUpdate(attrGroupRelation)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody attrGroupRelation: AttrGroupRelation): ResultDto {
        attrGroupRelationService.saveOrUpdate(attrGroupRelation)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        attrGroupRelationService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val attrGroupRelations = attrGroupRelationService.getAll()
        return resultSuccess().put("attrGroupRelation", attrGroupRelations.toList())
    }

    @GetMapping("{attrgroupid}/attr")
    @ApiOperation("get attr relation data by attrgroupid")
    suspend fun getAttrByAttrGroupId(@PathVariable("attrgroupid") attrGroupId: Long): ResultDto {
        val attrs = attrGroupRelationService.getAttrByAttrGroupId(attrGroupId)
        return resultSuccess().put("attr", attrs)
    }
}
