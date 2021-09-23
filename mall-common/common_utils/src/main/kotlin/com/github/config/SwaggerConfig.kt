package com.github.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.util.function.Predicate

/**
 * @author Andochiwa
 * @version 1.0
 * @since 09-24-03:33
 */
@Configuration
class SwaggerConfig {

    @Bean
    fun webApiConfig(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .paths(Predicate.not(PathSelectors.regex("/admin/.*")))
            .paths(Predicate.not(PathSelectors.regex("/error/.*")))
            .build()
    }

    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Mall project api document")
            .description("This document defines the mall project api")
            .version("1.0")
            .contact(Contact("Andochiwa", "https://github.com/andochiwa", "a1066079469@gmail.com"))
            .build()
    }
}
