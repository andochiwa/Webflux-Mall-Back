package com.github.coupon.dao

import com.github.coupon.entity.MemberPrice
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:41:21
 */
interface MemberPriceDao : CoroutineCrudRepository<MemberPrice, Long> {

}
