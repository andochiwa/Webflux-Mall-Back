package com.github.product.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ResourceLoader
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer


/**
 * @author Andochiwa
 * @version 1.0
 * @since 11-13-02:53
 */
@Configuration
class RedisConfiguration {

    @Bean
    fun reactiveRedisTemplate(
        reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory,
        resourceLoader: ResourceLoader,
        mapper: ObjectMapper
    ): ReactiveRedisTemplate<String, Any> {
        val stringSerializer = StringRedisSerializer()
        val jsonSerializer = Jackson2JsonRedisSerializer(Any::class.java)
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        mapper.configure(MapperFeature.USE_ANNOTATIONS, false)
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        // 解决jackson2无法反序列化LocalDateTime的问题
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        jsonSerializer.setObjectMapper(mapper)

        val redisSerializationContext = RedisSerializationContext
            .newSerializationContext<String, Any>().key(stringSerializer).value(jsonSerializer)
            .hashKey(stringSerializer).hashValue(jsonSerializer).build()
        return ReactiveRedisTemplate(reactiveRedisConnectionFactory, redisSerializationContext)
    }
}
