package com.github.coupon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.github")
class CouponApplication {
}

fun main(args: Array<String>) {
    runApplication<CouponApplication>(*args)
}
