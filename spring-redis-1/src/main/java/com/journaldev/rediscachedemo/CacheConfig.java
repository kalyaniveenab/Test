/*
 * package com.journaldev.rediscachedemo; import
 * org.springframework.cache.CacheManager; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.data.redis.cache.RedisCacheManager; import
 * org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
 * import org.springframework.data.redis.core.RedisTemplate; import
 * org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
 * import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
 * import org.springframework.data.redis.serializer.StringRedisSerializer;
 * 
 * 
 * 
 * 
 *//**
    * Created by freezhan on 16/9/5.
    *//*
       * @Configuration public class CacheConfig {
       * 
       * 
       * @Bean JedisConnectionFactory jedisConnectionFactory() { return new
       * JedisConnectionFactory(); }
       * 
       * @Bean(name="redisTemplate") public RedisTemplate<String, Object>
       * redisTemplate() { final RedisTemplate<String, Object> template = new
       * RedisTemplate<String, Object>();
       * template.setConnectionFactory(jedisConnectionFactory());
       * template.setKeySerializer(new StringRedisSerializer());
       * template.setValueSerializer(new
       * Jackson2JsonRedisSerializer<User>(User.class)); return template; }
       * 
       * @Bean public CacheManager cacheManager() { RedisCacheManager cacheManager =
       * new RedisCacheManager(redisTemplate()); return cacheManager; }
       * 
       * }
       * 
       */