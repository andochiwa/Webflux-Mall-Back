package com.github.search.dao

import com.github.search.entity.SkuEntity
import org.springframework.data.repository.reactive.ReactiveSortingRepository

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-06-23:01
 */
interface SkuDao : ReactiveSortingRepository<SkuEntity, Long> {
}
