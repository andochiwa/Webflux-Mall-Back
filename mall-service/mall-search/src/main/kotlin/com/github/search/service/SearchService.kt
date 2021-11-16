package com.github.search.service

import com.github.search.constant.EsPageConstant
import com.github.search.dto.SearchDto
import com.github.search.entity.SkuEntity
import com.github.search.vo.SearchParamVo
import kotlinx.coroutines.reactor.awaitSingle
import org.apache.lucene.search.join.ScoreMode
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.aggregations.AggregationBuilders
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder
import org.elasticsearch.search.sort.SortBuilders
import org.elasticsearch.search.sort.SortOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate
import org.springframework.data.elasticsearch.core.SearchPage
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.data.elasticsearch.core.query.Query
import org.springframework.stereotype.Service

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-15-19:11
 */
@Service
class SearchService {
    @Autowired
    lateinit var template: ReactiveElasticsearchTemplate

    suspend fun search(searchParam: SearchParamVo): SearchDto {
        val query = buildSearchRequest(searchParam)
        val searchPage = template.searchForPage(query, SkuEntity::class.java).awaitSingle()
        return buildSearchResult(searchPage)
    }

    private fun buildSearchResult(searchPage: SearchPage<SkuEntity>): SearchDto {
        val searchDto = SearchDto()
        searchDto.totalNum = searchPage.totalElements
        searchDto.totalPage = searchPage.totalPages.toLong()
        searchDto.pageNum = searchPage.number + 1

        val searchHits = searchPage.searchHits
        searchDto.products = searchHits.map {
            it.highlightFields["skuTitle"]?.get(0)?.run { it.content.skuTitle = this }
            it.content
        }.toList()

        val catelogAgg = searchHits.aggregations!!.get<ParsedLongTerms>("catelogAgg")
        val brandAgg = searchHits.aggregations!!.get<ParsedLongTerms>("brandAgg")
        val attrAgg = searchHits.aggregations!!.get<ParsedNested>("attrAgg")

        val catelogVoList = mutableListOf<SearchDto.CatelogVo>()
        val brandVoList = mutableListOf<SearchDto.BrandVo>()
        val attrVoList = mutableListOf<SearchDto.AttrVo>()

        catelogAgg.buckets.forEach { bucket ->
            val catelogVo = SearchDto.CatelogVo()
            catelogVo.catelogId = bucket.keyAsString.toLong()
            val nameAgg = bucket.aggregations.get<ParsedStringTerms>("catelogNameAgg")
            catelogVo.catelogName = nameAgg.buckets[0]?.keyAsString
            catelogVoList += catelogVo
        }

        attrAgg.aggregations.get<ParsedLongTerms>("attrIdAgg").buckets.forEach { bucket ->
            val attrVo = SearchDto.AttrVo()
            attrVo.attrId = bucket.keyAsNumber.toLong()
            val nameAgg = bucket.aggregations.get<ParsedStringTerms>("attrNameAgg")
            attrVo.attrName = nameAgg.buckets[0]?.keyAsString

            val valueAgg = bucket.aggregations.get<ParsedStringTerms>("attrValueAgg")
            val attrValues = mutableListOf<String>()
            valueAgg.buckets.forEach { attrValues += it.keyAsString }
            attrVo.attrValue = attrValues
            attrVoList += attrVo
        }

        brandAgg.buckets.forEach { bucket ->
            val brandVo = SearchDto.BrandVo()
            brandVo.brandId = bucket.keyAsString.toLong()
            val imgAgg = bucket.aggregations.get<ParsedStringTerms>("brandImgAgg")
            val nameAgg = bucket.aggregations.get<ParsedStringTerms>("brandNameAgg")
            brandVo.brandImg = imgAgg.buckets[0]?.keyAsString
            brandVo.brandName = nameAgg.buckets[0]?.keyAsString
            brandVoList += brandVo
        }

        searchDto.catelogs = catelogVoList
        searchDto.brands = brandVoList
        searchDto.attrs = attrVoList

        return searchDto
    }

    private fun buildSearchRequest(searchParam: SearchParamVo): Query {
        val boolQuery = QueryBuilders.boolQuery()
        // 按title查询
        searchParam.keyword?.run { boolQuery.must(QueryBuilders.matchQuery("skuTitle", this)) }
        // 按catelog3Id查询
        searchParam.catelog3Id?.run { boolQuery.filter(QueryBuilders.termQuery("catelogId", this)) }
        // 按所有品牌id查询
        searchParam.brandId?.forEach {
            boolQuery.filter(QueryBuilders.termQuery("brandId", it))
        }
        // 按照所有指定属性查询
        searchParam.attrs?.forEach { attrs ->
            val nestedBoolQuery = QueryBuilders.boolQuery()
            val split = attrs.split("_")
            val attrId = split[0]
            val attrValues = split[1].split(":")
            nestedBoolQuery.must(QueryBuilders.termQuery("attrs.attrId", attrId))
            nestedBoolQuery.must(QueryBuilders.termsQuery("attrs.attrValue", attrValues))
            val nestedQuery = QueryBuilders.nestedQuery("attrs", nestedBoolQuery, ScoreMode.None)
            boolQuery.filter(nestedQuery)
        }
        // 按照是否有库存查询
        searchParam.hasStock?.run { boolQuery.filter(QueryBuilders.termQuery("hasStock", true)) }
        // 按照价格区间查询
        searchParam.skuPrice?.run {
            val split = this.split("_")
            val rangeQuery = QueryBuilders.rangeQuery("skuPrice")
            if (split[0].isNotBlank()) {
                rangeQuery.gte(split[0])
            }
            if (split[1].isNotBlank()) {
                rangeQuery.lte(split[1])
            }
            boolQuery.filter(rangeQuery)
        }
        val nativeSearchQueryBuilder = NativeSearchQueryBuilder()
            .withQuery(boolQuery)
        // 排序
        searchParam.sort?.run {
            val split = this.split("_")
            val sortBuilder = SortBuilders.fieldSort(split[0]).order(SortOrder.fromString(split[1]))
            nativeSearchQueryBuilder.withSort(sortBuilder)
        }
        // 分页
        nativeSearchQueryBuilder.withPageable(PageRequest.of(searchParam.pageNum - 1, EsPageConstant.DEFAULT_SIZE.size))
        // 高亮
        searchParam.keyword?.run {
            val highlightBuilder = HighlightBuilder()
            highlightBuilder.field("skuTitle").preTags("<b style='color:red'>").postTags("</b>")
            nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder)
        }
        // 聚合分析
        // 品牌聚合
        val brandAgg = AggregationBuilders.terms("brandAgg")
        brandAgg.field("brandId").size(50)
        // 品牌聚合子聚合
        brandAgg.subAggregation(AggregationBuilders.terms("brandNameAgg").field("brandName").size(1))
        brandAgg.subAggregation(AggregationBuilders.terms("brandImgAgg").field("brandImg").size(1))
        // 分类聚合
        val catelogAgg = AggregationBuilders.terms("catelogAgg").field("catelogId").size(20)
        catelogAgg.subAggregation(AggregationBuilders.terms("catelogNameAgg").field("catelogName").size(1))
        // 属性聚合
        val attrAgg = AggregationBuilders.nested("attrAgg", "attrs")

        attrAgg.subAggregation(
            AggregationBuilders.terms("attrIdAgg").field("attrs.attrId")
                .subAggregation(AggregationBuilders.terms("attrNameAgg").field("attrs.attrName").size(1))
                .subAggregation(AggregationBuilders.terms("attrValueAgg").field("attrs.attrValue").size(50))
        )
        return nativeSearchQueryBuilder
            .addAggregation(brandAgg)
            .addAggregation(catelogAgg)
            .addAggregation(attrAgg)
            .build()
    }
}
