package com.github.thirdparty.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * @author Andochiwa
 * @version 1.0
 * @since 12-02-18:31
 */
@Configuration
class ThreadPoolConfiguration {

    @Bean
    fun emailThreadPool(): ThreadPoolExecutor {
        return ThreadPoolExecutor(
            20, 40, 1, TimeUnit.MINUTES,
            LinkedBlockingQueue(),
            Executors.defaultThreadFactory(),
            ThreadPoolExecutor.DiscardOldestPolicy()
        )
    }
}
