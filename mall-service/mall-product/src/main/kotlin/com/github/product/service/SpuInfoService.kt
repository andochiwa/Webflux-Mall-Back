package com.github.product.service

import com.github.product.dao.*
import com.github.product.entity.*
import com.github.product.feign.CouponFeign
import com.github.product.vo.SpuSaveVo
import com.github.to.SkuFullReductionTo
import com.github.to.SpuBoundTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.isEqual
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 *
 * @author Andochiwa
 * @email a1066079469@gmail.com
 * @date 2021-09-24 00:47:19
 */
@Service
class SpuInfoService {
    @Autowired
    lateinit var spuInfoDao: SpuInfoDao

    @Autowired
    lateinit var spuInfoDescDao: SpuInfoDescDao

    @Autowired
    lateinit var spuImagesDao: SpuImagesDao

    @Autowired
    lateinit var productAttrValueDao: ProductAttrValueDao

    @Autowired
    lateinit var attrDao: AttrDao

    @Autowired
    lateinit var skuInfoDao: SkuInfoDao

    @Autowired
    lateinit var skuImagesDao: SkuImagesDao

    @Autowired
    lateinit var skuSaleAttrValueDao: SkuSaleAttrValueDao

    @Autowired
    lateinit var couponFeign: CouponFeign

    @Autowired
    lateinit var template: R2dbcEntityTemplate

    suspend fun getById(id: Long): SpuInfo? {
        return spuInfoDao.findById(id)
    }

    suspend fun saveOrUpdate(spuInfo: SpuInfo): SpuInfo {
        return spuInfoDao.save(spuInfo)
    }

    suspend fun deleteById(id: Long) {
        spuInfoDao.deleteById(id)
    }

    fun getAll(): Flow<SpuInfo> {
        return spuInfoDao.findAll()
    }

    // todo: Distribute transaction
    @Transactional
    suspend fun saveSpu(spuSaveVo: SpuSaveVo) {
        // 保存spu的基本信息
        val spuInfo = SpuInfo().apply {
            createTime = LocalDateTime.now()
            updateTime = LocalDateTime.now()
        }
        BeanUtils.copyProperties(spuSaveVo, spuInfo)
        this.saveOrUpdate(spuInfo)

        // 保存spu的描述图片
        SpuInfoDesc().apply {
            description = spuSaveVo.description?.joinToString(",")
            spuId = spuInfo.id
        }.run { spuInfoDescDao.save(this) }

        // 保存spu的图片集
        val images = spuSaveVo.images
        images?.map {
            SpuImages().apply {
                spuId = spuInfo.id
                imgUrl = it
            }
        }?.run { spuImagesDao.saveAll(this).toList() }

        // 保存spu的规格参数
        val baseAttrs = spuSaveVo.baseAttrs
        baseAttrs?.map {
            ProductAttrValue().apply {
                spuId = spuInfo.id
                attrId = it.attrId
                attrName = attrDao.findById(attrId!!)?.attrName
                attrValue = it.attrValues
                quickShow = it.showDesc
            }
        }?.run { productAttrValueDao.saveAll(this).toList() }

        //// 保存对应的sku信息
        val skus = spuSaveVo.skus
        // sku的基本信息和图片信息
        val skuInfos = skus?.map {
            SkuInfo().apply {
                spuId = spuInfo.id
                skuName = it.skuName
                skuDesc = it.descar?.joinToString(",")
                catelogId = spuInfo.catelogId
                brandId = spuInfo.brandId
                it.images?.forEach { (defaultImg, imgUrl) ->
                    if (defaultImg == 1) {
                        skuDefaultImg = imgUrl
                        return@forEach
                    }
                }
                skuTitle = it.skuTitle
                skuSubtitle = it.skuSubtitle
                price = it.price
                saleCount = 0L
            }
        }?.run { skuInfoDao.saveAll(this).toList() }
        // sku的图片信息和销售属性
        var skuImages = mutableListOf<SkuImages>()
        val skuSaleAttrValues = mutableListOf<SkuSaleAttrValue>()
        skuInfos?.indices?.forEach { i ->
            skus[i].images?.indices?.forEach { j ->
                SkuImages().apply {
                    skuId = skuInfos[i].skuId
                    imgUrl = skus[i].images!![j].imgUrl
                    defaultImg = skus[i].images!![j].defaultImg
                }.run { skuImages += this }
            }
            skus[i].attr?.indices?.forEach { j ->
                SkuSaleAttrValue().apply {
                    skuId = skuInfos[i].skuId
                    attrId = skus[i].attr!![j].attrId
                    attrName = skus[i].attr!![j].attrName
                    attrValue = skus[i].attr!![j].attrValue
                }.run { skuSaleAttrValues += this }
            }
        }
        // filter image url is empty
        skuImages = skuImages.filter { it.imgUrl?.isNotBlank() ?: false } as MutableList<SkuImages>
        // save batch
        skuImagesDao.saveAll(skuImages).toList()
        skuSaleAttrValueDao.saveAll(skuSaleAttrValues).toList()

        // 保存spu的积分信息
        val bounds = spuSaveVo.bounds
        SpuBoundTo().apply {
            spuId = spuInfo.id
            growBounds = bounds?.growBounds
            buyBounds = bounds?.buyBounds
            work = 0
        }.run { couponFeign.saveSpuBounds(this).awaitSingle() }
            .run { if (code != 200) throw RuntimeException("spu bound远程服务保存失败") }

        // 保存sku的优惠信息
        val skuFullReductionToList = mutableListOf<SkuFullReductionTo>()
        skuInfos?.indices?.forEach { i ->
            SkuFullReductionTo().apply {
                skuId = skuInfos[i].skuId
                discount = spuSaveVo.skus!![i].discount
                countStatus = spuSaveVo.skus!![i].countStatus
                fullCount = spuSaveVo.skus!![i].fullCount
                fullPrice = spuSaveVo.skus!![i].fullPrice
                reducePrice = spuSaveVo.skus!![i].reducePrice
                price = spuSaveVo.skus!![i].price
                priceStatus = spuSaveVo.skus!![i].priceStatus
                memberPrice = spuSaveVo.skus!![i].memberPrice?.map {
                    val memberPriceTo = SkuFullReductionTo.MemberPriceTo()
                    BeanUtils.copyProperties(it, memberPriceTo)
                    memberPriceTo
                }
            }.run { skuFullReductionToList += this }
        }
        if (skuFullReductionToList.isNotEmpty()) {
            couponFeign.saveSkuReduction(skuFullReductionToList).awaitSingle()
                .run { if (code != 200) throw RuntimeException("sku reduction远程服务保存失败") }
        }
    }


    suspend fun geyListOnConditions(
        page: Int,
        limit: Int,
        field: String?,
        order: String?,
        key: String?,
        catelogId: Long?,
        brandId: Long?,
        status: Int?,
    ): Map<String, Any> {
        var criteria = Criteria.empty()
        if (catelogId?.equals(0L) == false) {
            criteria = criteria.and("catelog_id").isEqual(catelogId)
        }
        if (brandId?.equals(0L) == false) {
            criteria = criteria.and("brand_id").isEqual(brandId)
        }
        status?.run { criteria = criteria.and("publish_status").isEqual(status) }
        if (key?.isNotBlank() == true) {
            criteria = criteria.and("spu_name").like("%${key}%")
        }

        val sortOrder = if (order == "desc") Sort.Direction.DESC else Sort.Direction.ASC
        val pageRequest = field?.run { PageRequest.of(page, limit, Sort.by(sortOrder, field)) }
            ?: run { PageRequest.of(page, limit) }

        val spuInfoList = template
            .select(SpuInfo::class.java)
            .from("pms_spu_info")
            .matching(Query.query(criteria).with(pageRequest))
            .flow().toList()
        val count = template.count(Query.query(criteria), SpuInfo::class.java).awaitSingle()
        return mutableMapOf<String, Any>().apply {
            this["list"] = spuInfoList
            this["totalCount"] = count
        }
    }
}

