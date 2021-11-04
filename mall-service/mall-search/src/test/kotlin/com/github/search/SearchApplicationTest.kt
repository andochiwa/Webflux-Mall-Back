package com.github.search

import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-05-01:47
 */
@SpringBootTest
class SearchApplicationTest {

    @Autowired
    lateinit var esTemplate: ReactiveElasticsearchTemplate

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
}

data class User(
    var username: String? = null,

    var gender: String? = null,

    var age: Int? = null,
)
