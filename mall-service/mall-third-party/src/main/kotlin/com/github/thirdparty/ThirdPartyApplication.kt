package com.github.thirdparty

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

/**
 * @author Andochiwa
 * @version 1.0
 * @since 10-15-21:01
 */
@SpringBootApplication(exclude = [R2dbcAutoConfiguration::class])
@ComponentScan("com.github")
class ThirdPartyApplication {
}

fun main(args: Array<String>) {
    runApplication<ThirdPartyApplication>(*args)
}
