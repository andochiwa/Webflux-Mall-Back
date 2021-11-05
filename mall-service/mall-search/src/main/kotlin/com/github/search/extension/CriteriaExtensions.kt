package com.github.search.extension

import org.springframework.data.elasticsearch.core.query.Criteria

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-06-01:29
 */
fun Criteria.isEquals(value: Any): Criteria = `is`(value)
