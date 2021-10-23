package com.github.ware.dao

import com.github.ware.entity.PurchaseDetail
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:24
 */
interface PurchaseDetailDao : CoroutineCrudRepository<PurchaseDetail, Long> {

}
