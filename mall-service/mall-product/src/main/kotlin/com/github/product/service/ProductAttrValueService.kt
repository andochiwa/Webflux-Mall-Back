package com.github.product.service

import com.github.product.dao.ProductAttrValueDao
import com.github.product.entity.ProductAttrValue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.Update
import org.springframework.data.relational.core.query.isEqual
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-27 04:43:25
 */
@Service
class ProductAttrValueService {
    @Autowired
    lateinit var productAttrValueDao: ProductAttrValueDao

    @Autowired
    lateinit var template: R2dbcEntityTemplate

    suspend fun getBaseAttrValueBySpuId(spuId: Long): List<ProductAttrValue> {
        return productAttrValueDao.getAllBySpuId(spuId).toList()
    }

    @Transactional
    suspend fun updateBySpuId(spuId: Long, productAttrValue: List<ProductAttrValue>) {
        val criteriaTemplate = Criteria.where("spu_id").isEqual(spuId)
        productAttrValue.forEach {
            val criteria = criteriaTemplate.and("attr_id").isEqual(it.attrId!!)
            var update = Update.update("attr_value", it.attrValue)
            it.attrName?.run { update = update.set("attr_name", this) }
            it.quickShow?.run { update = update.set("quick_show", this) }
            template.update(Query.query(criteria), update, ProductAttrValue::class.java).awaitSingle()
        }
    }
}

