package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.AttrGroup
import com.github.product.service.AttrGroupService
import com.github.product.service.CategoryService
import com.github.product.vo.AttrAndGroupRelationVo
import com.github.vaild.AddGroup
import com.github.vaild.DeleteGroup
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
@RequestMapping("product/attrgroup")
@Api
class AttrGroupController {

    @Autowired
    lateinit var attrGroupService: AttrGroupService

    @Autowired
    lateinit var categoryService: CategoryService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val attrGroup = attrGroupService.getById(id)
        attrGroup?.catelogPath = categoryService.getCatelogPath(attrGroup?.catelogId!!)
        return resultSuccess().put("attrGroup", attrGroup)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@Validated(AddGroup::class) @RequestBody attrGroup: AttrGroup): ResultDto {
        attrGroupService.saveOrUpdate(attrGroup)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@Validated(UpdateGroup::class) @RequestBody attrGroup: AttrGroup): ResultDto {
        attrGroupService.saveOrUpdate(attrGroup)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        attrGroupService.deleteById(id)
        return resultSuccess()
    }

    @DeleteMapping
    @ApiOperation("deleteByIds")
    suspend fun deleteByIds(@RequestBody ids: List<Long>): ResultDto {
        attrGroupService.deleteByIds(ids)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val attrGroups = attrGroupService.getAll()
        return resultSuccess().put("attrGroup", attrGroups.toList())
    }

    @GetMapping("pagination")
    @ApiOperation("get pagination")
    suspend fun getPagination(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("key", required = false) key: String?
    ): ResultDto {
        val attrGroupMap = attrGroupService.getPagination(page - 1, limit, key)
        return resultSuccess().putAll(attrGroupMap)
    }

    @GetMapping("pagination/{categoryId}")
    @ApiOperation("get pagination by CategoryId")
    suspend fun getPaginationByCategoryId(
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("key", required = false) key: String?,
        @PathVariable("categoryId") categoryId: Long
    ): ResultDto {
        val attrGroupMap = attrGroupService.getPaginationByCategoryId(page - 1, limit, key, categoryId)
        return resultSuccess().putAll(attrGroupMap)
    }

    @GetMapping("{attrGroupId}/attr")
    @ApiOperation("get attr relation data by attrGroupId")
    suspend fun getAttrRelation(@PathVariable("attrGroupId") attrGroupId: Long): ResultDto {
        val attrs = attrGroupService.getAttrRelation(attrGroupId)
        return resultSuccess().put("attr", attrs)
    }

    @PostMapping("attr")
    suspend fun insertAttrRelation(@RequestBody @Validated(DeleteGroup::class) attrAndGroupRelationVo: List<AttrAndGroupRelationVo>): ResultDto {
        attrGroupService.insertAttrRelation(attrAndGroupRelationVo)
        return resultSuccess()
    }

    @GetMapping("{attrGroupId}/no-attr")
    @ApiOperation("get attr no relation data by attrGroupId")
    suspend fun getNoAttrRelation(
        @PathVariable("attrGroupId") attrGroupId: Long,
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("key", required = false) key: String?
    ): ResultDto {
        val attrMap = attrGroupService.getNoAttrRelation(attrGroupId, page - 1, limit, key)
        return resultSuccess().putAll(attrMap)
    }

    @GetMapping("{catelogId}/with-attr")
    @ApiOperation("get attr group with attrs by catelog id")
    suspend fun getAttrGroupWithAttrsByCatelogId(@PathVariable("catelogId") catelogId: Long): ResultDto {
        val attrGroupWithAttrsDtoList = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId)
        return resultSuccess().put("list", attrGroupWithAttrsDtoList)
    }
}
