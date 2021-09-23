package com.github.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.github")
class ProductApplication {
}

fun main(args: Array<String>) {
    runApplication<ProductApplication>(*args)
}
