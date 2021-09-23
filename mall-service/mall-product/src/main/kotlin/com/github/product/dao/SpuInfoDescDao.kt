package com.github.product.dao

import com.github.product.entity.SpuInfoDesc
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
interface SpuInfoDescDao : CoroutineCrudRepository<SpuInfoDesc, Long> {

}
