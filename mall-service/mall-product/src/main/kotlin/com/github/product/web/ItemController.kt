package com.github.product.web

import com.github.product.service.SkuInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-25-19:30
 */
@Controller
class ItemController {
    @Autowired
    lateinit var skuInfoService: SkuInfoService

    @GetMapping("{skuId}", "{skuId}.html")
    suspend fun skuItem(@PathVariable("skuId") skuId: Long, model: Model): String {
        val skuItem = skuInfoService.getSkuItem(skuId)
        model.addAttribute("result", skuItem)
        return "item"
    }
}
