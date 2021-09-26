package com.github.coupon.dao

import com.github.coupon.entity.SeckillSession
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:41:21
 */
interface SeckillSessionDao : CoroutineCrudRepository<SeckillSession, Long> {

}
