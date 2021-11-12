package com.github.product.web

import com.github.dto.ResultDto
import com.github.dto.resultSuccess
import com.github.product.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-08-20:12
 */
@Controller
class IndexController {

    @Autowired
    lateinit var categoryService: CategoryService

    @GetMapping("/", "/index", "index.html")
    suspend fun indexPage(model: Model): String {
        val categoryList = categoryService.getLevel1Category()

        model.addAttribute("category", categoryList)
        return "index"
    }
}
