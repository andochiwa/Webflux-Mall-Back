package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.CategoryBrandRelation
import com.github.product.service.CategoryBrandRelationService
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
@RequestMapping("product/categorybrandrelation")
@Api
class CategoryBrandRelationController {

    @Autowired
    lateinit var categoryBrandRelationService: CategoryBrandRelationService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val categoryBrandRelation = categoryBrandRelationService.getById(id)
        return resultSuccess().put("categoryBrandRelation", categoryBrandRelation)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@Validated(AddGroup::class) @RequestBody categoryBrandRelation: CategoryBrandRelation): ResultDto {
        categoryBrandRelationService.saveOrUpdate(categoryBrandRelation)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@Validated(UpdateGroup::class) @RequestBody categoryBrandRelation: CategoryBrandRelation): ResultDto {
        categoryBrandRelationService.saveOrUpdate(categoryBrandRelation)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        categoryBrandRelationService.deleteById(id)
        return resultSuccess()
    }

    @DeleteMapping
    @ApiOperation("deleteByIds")
    suspend fun deleteByIds(@RequestBody ids: List<Long>): ResultDto {
        categoryBrandRelationService.deleteByIds(ids)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val categoryBrandRelations = categoryBrandRelationService.getAll()
        return resultSuccess().put("categoryBrandRelation", categoryBrandRelations.toList())
    }

    @GetMapping("catelog/{brandId}")
    @ApiOperation("Get a list of all categories associated with the brand")
    suspend fun getCatelogByBranId(@PathVariable("brandId") brandId: Long): ResultDto {
        val categoryBrandRelations = categoryBrandRelationService.getCatelogByBranId(brandId)
        return resultSuccess().put("categoryBrandRelation", categoryBrandRelations.toList())
    }
}
