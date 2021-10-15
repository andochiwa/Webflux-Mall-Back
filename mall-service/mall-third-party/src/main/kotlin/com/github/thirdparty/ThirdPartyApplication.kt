package com.github.thirdparty

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Andochiwa
 * @version 1.0
 * @since 10-15-21:01
 */
@SpringBootApplication
class ThirdPartyApplication {
}

fun main(args: Array<String>) {
    runApplication<ThirdPartyApplication>(*args)
}
