package com.github.coupon.controller

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.coupon.entity.HomeSubject
import com.github.coupon.service.HomeSubjectService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 23:39:59
 */
@RestController
@RequestMapping("coupon/homesubject")
@Api
class HomeSubjectController {

    @Autowired
    lateinit var homeSubjectService: HomeSubjectService

    @GetMapping("{id}")
    @ApiOperation("get")
    suspend fun getById(@PathVariable("id") id: Long): ResultDto {
        val homeSubject = homeSubjectService.getById(id)
        return resultSuccess().put("homeSubject", homeSubject)
    }

    @PostMapping
    @ApiOperation("insert")
    suspend fun insert(@RequestBody homeSubject: HomeSubject): ResultDto {
        homeSubjectService.saveOrUpdate(homeSubject)
        return resultSuccess()
    }

    @PutMapping
    @ApiOperation("update")
    suspend fun update(@RequestBody homeSubject: HomeSubject): ResultDto {
        homeSubjectService.saveOrUpdate(homeSubject)
        return resultSuccess()
    }

    @DeleteMapping("{id}")
    @ApiOperation("deleteById")
    suspend fun deleteById(@PathVariable("id") id: Long): ResultDto {
        homeSubjectService.deleteById(id)
        return resultSuccess()
    }

    @GetMapping
    @ApiOperation("getAll")
    suspend fun getAll(): ResultDto {
        val homeSubjects = homeSubjectService.getAll()
        return resultSuccess().put("homeSubject", homeSubjects.toList())
    }
}
