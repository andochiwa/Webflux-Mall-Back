package com.github.product.dao

import com.github.product.entity.ProductAttrValue
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:43:25
 */
interface ProductAttrValueDao : CoroutineCrudRepository<ProductAttrValue, Long> {

}
