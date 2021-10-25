package com.github.product.dao

import com.github.product.entity.SpuInfo
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
interface SpuInfoDao : CoroutineCrudRepository<SpuInfo, Long> {

    @Modifying
    @Query("update pms_spu_info set publish_status = :status where id = :id;")
    suspend fun updatePublishStatusById(id: Long, status: Int)
}
