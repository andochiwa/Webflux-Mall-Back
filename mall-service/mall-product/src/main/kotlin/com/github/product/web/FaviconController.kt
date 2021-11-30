package com.github.product.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-01-05:44
 */
@RestController
class FaviconController {

    @GetMapping("favicon.ico")
    fun favicon() {
    }
}
