package com.github.constant

/**
 * @author Andochiwa
 * @version 1.0
 * @since 10-25-05:18
 */
enum class PurchaseStatusEnum(val value: Int, val info: String) {
    CREATES(0, "新建"),
    ASSIGNED(1, "已分配"),
    RECEIVE(2, "已领取"),
    FINISH(3, "已完成"),
    HAS_ERROR(4, "有异常"),
}

enum class PurchaseDetailStatusEnum(val value: Int, val info: String) {
    CREATES(0, "新建"),
    ASSIGNED(1, "已分配"),
    BUYING(2, "正在采购"),
    FINISH(3, "已完成"),
    BUY_FAIL(4, "采购失败"),
}
