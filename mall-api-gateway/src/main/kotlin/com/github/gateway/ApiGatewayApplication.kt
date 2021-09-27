package com.github.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

/**
 * @author Andochiwa
 * @version 1.0
 * @since 09-28-01:59
 */
@SpringBootApplication
@ComponentScan("com.github")
class ApiGatewayApplication {
}

fun main(args: Array<String>) {
    runApplication<ApiGatewayApplication>(*args)
}
