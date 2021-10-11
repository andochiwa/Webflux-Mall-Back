package com.github.member.dao

import com.github.member.entity.GrowthChangeHistory
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:43:36
 */
interface GrowthChangeHistoryDao : CoroutineCrudRepository<GrowthChangeHistory, Long> {

}