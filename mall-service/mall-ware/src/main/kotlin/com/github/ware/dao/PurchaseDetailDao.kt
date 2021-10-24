package com.github.ware.dao

import com.github.ware.entity.PurchaseDetail
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:24
 */
interface PurchaseDetailDao : CoroutineCrudRepository<PurchaseDetail, Long> {

    @Modifying
    @Query("update wms_purchase_detail set purchase_id = :purchaseId, status = :status where id = :id;")
    suspend fun updatePurchaseIdAndStatusById(id: Long, purchaseId: Long, status: Int)
}
