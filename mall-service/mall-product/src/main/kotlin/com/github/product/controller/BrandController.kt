package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.Brand
import com.github.product.service.BrandService
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
@RequestMapping("product/brand")
@Api
class BrandController {

    @Autowired
    lateinit var brandService: BrandService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val brand = brandService.getById(id)
        return resultSuccess().put("brand", brand)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody brand: Brand): ResultDto {
        brandService.saveOrUpdate(brand)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody brand: Brand): ResultDto {
        brandService.saveOrUpdate(brand)
        return resultSuccess()
    }

    @DeleteMapping
    @ApiOperation("deleteByIds")
    suspend fun deleteById(@RequestBody id: List<Long>): ResultDto {
        brandService.deleteByIds(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val brands = brandService.getAll()
        return resultSuccess().put("brand", brands.toList())
    }

    @GetMapping("pagination")
    @ApiOperation("get pagination")
    suspend fun getPagination(@RequestParam("page") page: Int,
                              @RequestParam("limit") limit: Int,
                              @RequestParam("key", required = false) key: String?): ResultDto {
        val brandMap = brandService.getPagination(page - 1, limit, key)
        return resultSuccess().putAll(brandMap)
    }
}
