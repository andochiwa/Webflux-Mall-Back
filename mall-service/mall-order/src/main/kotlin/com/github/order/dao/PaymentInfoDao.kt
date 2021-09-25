package com.github.order.dao

import com.github.order.entity.PaymentInfo
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:18:13
 */
interface PaymentInfoDao : CoroutineCrudRepository<PaymentInfo, Long> {

}
