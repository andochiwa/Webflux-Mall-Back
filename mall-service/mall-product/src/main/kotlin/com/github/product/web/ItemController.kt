package com.github.product.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-25-19:30
 */
@Controller
class ItemController {

    @GetMapping("{skuId}", "{skuId}.html")
    fun skuItem(@PathVariable("skuId") skuId: Long): String {
        return "item"
    }
}
