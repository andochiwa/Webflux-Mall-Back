package com.github.product.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.entity.ProductAttrValue
import com.github.product.service.AttrService
import com.github.product.service.ProductAttrValueService
import com.github.product.vo.AttrAndGroupRelationVo
import com.github.product.vo.AttrVo
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
@RequestMapping("product/attr")
@Api
class AttrController {

    @Autowired
    lateinit var attrService: AttrService

    @Autowired
    lateinit var productAttrValueService: ProductAttrValueService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val attrDto = attrService.getById(id)
        return resultSuccess().put("attr", attrDto)
    }

    @GetMapping("{attrType}/list/{catelogId}")
    @ApiOperation("get base attr list")
    suspend fun getBaseAttrList(
        @PathVariable("attrType") attrType: Int,
        @PathVariable("catelogId") catelogId: Long,
        @RequestParam("page") page: Int,
        @RequestParam("limit") limit: Int,
        @RequestParam("key", required = false) key: String?,
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
    suspend fun update(@Validated(UpdateGroup::class) @RequestBody attrVo: AttrVo): ResultDto {
        attrService.update(attrVo)
        return resultSuccess()
    }

    @DeleteMapping
    @ApiOperation("deleteByIds")
    suspend fun deleteByIds(@RequestBody ids: List<Long>): ResultDto {
        attrService.deleteByIds(ids)
        return resultSuccess()
    }

    @DeleteMapping("attrgroup/relation")
    @ApiOperation("delete attr and attrgroup relation")
    suspend fun deleteAttrAndGroupRelation(
        @Validated(DeleteGroup::class) @RequestBody attrAndGroupRelationVo: AttrAndGroupRelationVo,
    ): ResultDto {
        attrService.deleteAttrAndGroupRelation(attrAndGroupRelationVo)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val attrs = attrService.getAll()
        return resultSuccess().put("attr", attrs.toList())
    }

    @GetMapping("/base/spu/{spuId}")
    @ApiOperation("get attr value list by spuId of base type")
    suspend fun getBaseAttrValueBySpuId(@PathVariable("spuId") spuId: Long): ResultDto {
        val list = productAttrValueService.getBaseAttrValueBySpuId(spuId)
        return resultSuccess().put("list", list)
    }

    @PutMapping("/update/{spuId}")
    @ApiOperation("update by spuId")
    suspend fun updateBySpuId(
        @PathVariable("spuId") spuId: Long,
        @RequestBody productAttrValue: List<ProductAttrValue>,
    ): ResultDto {
        productAttrValueService.updateBySpuId(spuId, productAttrValue)
        return resultSuccess()
    }
}
