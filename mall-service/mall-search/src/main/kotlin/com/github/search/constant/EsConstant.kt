package com.github.search.constant

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-06-22:11
 */
enum class EsIndexConstant(val index: String) {
    PRODUCT_INDEX("product")
}

enum class EsPageConstant(val size: Int) {
    DEFAULT_SIZE(16)
}
