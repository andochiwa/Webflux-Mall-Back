package com.github.search.web

import com.github.search.service.SearchService
import com.github.search.vo.SearchParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-15-18:36
 */
@Controller
class SearchController {

    @Autowired
    lateinit var searchService: SearchService

    @GetMapping("list", "list.html")
    suspend fun listPage(searchParam: SearchParam): String {

        searchService.search(searchParam)

        return "list"
    }
}
