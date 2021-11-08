package com.github.search.service

import com.github.search.constant.EsIndexConstant
import com.github.search.entity.SkuEntity
import com.google.gson.Gson
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.common.xcontent.XContentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate
import org.springframework.stereotype.Service

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-06-22:08
 */
@Service
class EsSaveService {
    @Autowired
    lateinit var template: ReactiveElasticsearchTemplate

    suspend fun putOnProduct(skuList: List<SkuEntity>) {
        val bulkRequest = BulkRequest()
        val gson = Gson()
        skuList.forEach {
            val indexRequest = IndexRequest(EsIndexConstant.PRODUCT_INDEX.index)
            indexRequest.id(it.skuId!!.toString())
            val skuJson = gson.toJson(it)
            indexRequest.source(skuJson, XContentType.JSON)
            bulkRequest.add(indexRequest)
        }

        template.execute { it.bulk(bulkRequest) }.asFlow().toList()
    }


}
