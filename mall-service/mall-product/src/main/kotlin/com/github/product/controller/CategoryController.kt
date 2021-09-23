package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.Category
import com.github.product.service.CategoryService
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
@RequestMapping("product/category")
@Api
class CategoryController {

    @Autowired
    lateinit var categoryService: CategoryService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val category = categoryService.getById(id)
        return resultSuccess().put("category", category)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody category: Category): ResultDto {
        categoryService.saveOrUpdate(category)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody category: Category): ResultDto {
        categoryService.saveOrUpdate(category)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        categoryService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val categorys = categoryService.getAll()
        return resultSuccess().put("category", categorys.toList())
    }
}
