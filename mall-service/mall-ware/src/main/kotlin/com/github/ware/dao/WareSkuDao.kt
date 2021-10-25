package com.github.ware.dao

import com.github.ware.entity.WareSku
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-26 03:53:23
 */
interface WareSkuDao : CoroutineCrudRepository<WareSku, Long> {

    suspend fun countBySkuIdAndWareId(skuId: Long, wareId: Long): Long

    @Modifying
    @Query("update wms_ware_sku set stock = stock + :skuNum where sku_id = :skuId and ware_id = :wareId;")
    suspend fun updateStockBySkuIdAndWareId(skuId: Long, wareId: Long, skuNum: Int?)
}
