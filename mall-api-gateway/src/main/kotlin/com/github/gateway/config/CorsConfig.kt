package com.github.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.util.pattern.PathPatternParser

/**
 * @author Andochiwa
 * @version 1.0
 * @since 09-29-02:37
 */
@Configuration
class CorsConfig {

    @Bean
    fun corsWebFilter(): CorsWebFilter {
        val corsConfiguration = CorsConfiguration().apply {
            allowCredentials = true
            addAllowedHeader("*")
            addAllowedOriginPattern("*")
            addAllowedMethod("*")
        }

        val source = UrlBasedCorsConfigurationSource(PathPatternParser()).apply {
            registerCorsConfiguration("/**", corsConfiguration)
        }
        return CorsWebFilter(source)
    }
}
