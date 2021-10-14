package com.github.product.dao

import com.github.product.entity.Brand
import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
interface BrandDao : CoroutineCrudRepository<Brand, Long> {

    fun findBy(pageable: Pageable): Flow<Brand>

    fun findByNameContaining(name: String, pageable: Pageable): Flow<Brand>
}
