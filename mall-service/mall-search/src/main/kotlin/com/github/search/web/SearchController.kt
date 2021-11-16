package com.github.search.web

import com.github.search.service.SearchService
import com.github.search.vo.SearchParamVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-15-18:36
 */
@Controller
@Api
class SearchController {

    @Autowired
    lateinit var searchService: SearchService

    @GetMapping("list", "list.html")
    @ApiOperation("route and search")
    suspend fun listPage(searchParam: SearchParamVo): String {

        val searchDto = searchService.search(searchParam)

        return "list"
    }
}
