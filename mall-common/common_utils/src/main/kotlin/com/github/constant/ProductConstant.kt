package com.github.constant

/**
 * @author Andochiwa
 * @version 1.0
 * @since 10-22-02:05
 */
enum class AttrEnum(val value: Int, val info: String) {
    ATTR_TYPE_BASE(1, "基本属性"),
    ATTR_TYPE_SALE(0, "销售属性")
}

enum class SpuPublishStatusEnum(val value: Int, val info: String) {
    CREATES(0, "新建"),
    PUT_ON(1, "上架"),
    PULL_ON(2, "下架")
}

enum class AttrSearchEnum(val value: Int, val info: String) {
    ATTR_CAN_SEARCH(1, "可被检索"),
    ATTR_CANNOT_SEARCH(0, "不可被检索")
}
