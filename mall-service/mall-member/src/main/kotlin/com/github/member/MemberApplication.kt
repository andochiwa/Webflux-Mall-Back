package com.github.member

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.github")
class MemberApplication {
}

fun main(args: Array<String>) {
    runApplication<MemberApplication>(*args)
}
