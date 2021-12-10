package com.github.cart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.runApplication
import reactivefeign.spring.config.EnableReactiveFeignClients

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-10-17:18
 */
@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class, R2dbcAutoConfiguration::class])
@EnableReactiveFeignClients
class CartApplication

fun main(args: Array<String>) {
    runApplication<CartApplication>(*args)
}
