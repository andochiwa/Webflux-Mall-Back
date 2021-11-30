package com.github.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-30-21:51
 */
@SpringBootApplication(exclude = [R2dbcAutoConfiguration::class, DataSourceAutoConfiguration::class])
@ComponentScan("com.github")
class AuthApplication

fun main(args: Array<String>) {
    runApplication<AuthApplication>(*args)
}
