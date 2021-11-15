package com.github.search.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-15-18:36
 */
@Controller
class SearchController {

    @GetMapping("list", "list.html")
    fun listPage(): String {
        return "list"
    }
}
