package com.maoyan.ffcommunity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Redis序列化配置
 *
 * @Author: 猫颜
 * @Date: 2023/3/5
 * @Description: Redis配置
 * 这段代码是一个 Java Spring 的 Redis 配置类，它定义了一个名为 jsonRedisTemplate 的 Bean。这个 Bean 是一个 RedisTemplate，它的 key 和 value 都是 Object 类型。
 * 在 Redis 中，数据通常存储为二进制格式，但是在这个配置中，通过使用 Jackson2JsonRedisSerializer 对象，将对象序列化为 JSON 格式进行存储。这使得存储在 Redis 中的数据可以被其他应用程序更方便地使用和解析。
 * 这个配置类可以在其他类中使用 @Autowired 注解进行自动注入，从而方便地使用 RedisTemplate 对 Redis 进行操作。
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Object> jsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> objectObjectRedisTemplate = new RedisTemplate<>();
        objectObjectRedisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        objectObjectRedisTemplate.setDefaultSerializer(objectJackson2JsonRedisSerializer);
        return objectObjectRedisTemplate;
    }
}
