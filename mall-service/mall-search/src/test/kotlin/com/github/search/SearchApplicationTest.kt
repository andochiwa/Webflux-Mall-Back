package com.github.search

import com.github.search.extension.isEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-05-01:47
 */
@SpringBootTest
class SearchApplicationTest {

    @Autowired
    lateinit var esTemplate: ReactiveElasticsearchTemplate

    val log: Logger = LoggerFactory.getLogger(SearchApplicationTest::class.java)

    @Test
    fun test() {
        println(esTemplate)
    }

    @Test
    fun indexTest() {
        runBlocking {
            val user = User("mary", "男", 18)
            val indexCoordinates = IndexCoordinates.of("users")
            println(esTemplate.save(user, indexCoordinates).awaitSingle())
        }
    }

    @Test
    fun searchTest() {
        val criteria = Criteria.where("address").isEquals("mill")
        val query = CriteriaQuery(criteria)
        val searchFlux = esTemplate.search(query, Any::class.java, IndexCoordinates.of("bank"))
        runBlocking {
            log.info(searchFlux.asFlow().toList().toString())
        }
    }

    data class User(
        var username: String? = null,

        var gender: String? = null,

        var age: Int? = null,
    )
}
