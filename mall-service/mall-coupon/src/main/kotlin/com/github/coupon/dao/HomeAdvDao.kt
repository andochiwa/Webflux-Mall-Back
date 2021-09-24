package com.github.coupon.dao

import com.github.coupon.entity.HomeAdv
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 23:39:59
 */
interface HomeAdvDao : CoroutineCrudRepository<HomeAdv, Long> {

}
