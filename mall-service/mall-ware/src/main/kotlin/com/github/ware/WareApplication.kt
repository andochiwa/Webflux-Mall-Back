package com.github.ware

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WareApplication {
}

fun main(args: Array<String>) {
    runApplication<WareApplication>(*args)
}
