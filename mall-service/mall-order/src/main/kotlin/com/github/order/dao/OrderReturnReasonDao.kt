package com.github.order.dao

import com.github.order.entity.OrderReturnReason
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:18:14
 */
interface OrderReturnReasonDao : CoroutineCrudRepository<OrderReturnReason, Long> {

}
