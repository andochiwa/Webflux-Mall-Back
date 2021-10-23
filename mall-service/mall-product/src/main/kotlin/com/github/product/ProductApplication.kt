package com.github.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import reactivefeign.spring.config.EnableReactiveFeignClients

@SpringBootApplication
@ComponentScan("com.github")
@EnableReactiveFeignClients
class ProductApplication {
}

fun main(args: Array<String>) {
    runApplication<ProductApplication>(*args)
}
