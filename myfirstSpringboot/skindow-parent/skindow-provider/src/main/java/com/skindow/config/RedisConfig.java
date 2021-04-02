package com.skindow.config;

import com.skindow.util.RedisSerialUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Duration;

/**redis配置类
 * Created by Administrator on 2019/8/19.
 */
@Component
public class RedisConfig {

    /**创建redis连接工厂
     * @param defaultRedisConfig
     * @param defaultPoolConfig
     * @return
     */
    @Bean
    @ConditionalOnBean(name = "defaultRedisConfig")
    public LettuceConnectionFactory defaultLettuceConnectionFactory(RedisStandaloneConfiguration defaultRedisConfig,
                                                                    GenericObjectPoolConfig defaultPoolConfig) {
        LettuceClientConfiguration clientConfig =
                LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(100))
                        .poolConfig(defaultPoolConfig).build();
        return new LettuceConnectionFactory(defaultRedisConfig, clientConfig);
    }

    /** 给redisTemplate配置连接工厂
     * @param defaultLettuceConnectionFactory
     * @return
     */
    @Bean
    @ConditionalOnBean(name = "defaultLettuceConnectionFactory")
    public RedisTemplate<String, String> defaultRedisTemplate(
            LettuceConnectionFactory defaultLettuceConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(defaultLettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Configuration
    @ConditionalOnProperty(name = "host", prefix = "spring.redis")
    public static class DefaultRedisConfig {
        @Value("${spring.redis.host}")
        private String host;
        @Value("${spring.redis.port}")
        private Integer port;
        @Value("${spring.redis.password}")
        private String password;
        @Value("${spring.redis.database}")
        private Integer database;

        @Value("${spring.redis.lettuce.pool.max-active}")
        private Integer maxActive;
        @Value("${spring.redis.lettuce.pool.max-idle}")
        private Integer maxIdle;
        @Value("${spring.redis.lettuce.pool.max-wait}")
        private Long maxWait;
        @Value("${spring.redis.lettuce.pool.min-idle}")
        private Integer minIdle;

        /** 配置读取到的连接池配置信息
         * @return
         */
        @Bean
        public GenericObjectPoolConfig defaultPoolConfig() {
            GenericObjectPoolConfig config = new GenericObjectPoolConfig();
            config.setMaxTotal(maxActive);
            config.setMaxIdle(maxIdle);
            config.setMinIdle(minIdle);
            config.setMaxWaitMillis(maxWait);
            return config;
        }

        /** 配置读取到的redis连接配置信息
         * @return
         */
        @Bean
        public RedisStandaloneConfiguration defaultRedisConfig() {
            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
            config.setHostName(host);
            config.setPassword(RedisPassword.of(password));
            config.setPort(port);
            config.setDatabase(database);
            return config;
        }

        @Bean
        public RedisTemplate<Serializable, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
            RedisTemplate<Serializable, Object> template = new RedisTemplate<Serializable, Object>();
            template.setConnectionFactory(connectionFactory);
            template.afterPropertiesSet();
            // redis存取对象的关键配置
            template.setKeySerializer(new StringRedisSerializer());
            // ObjectRedisSerializer类为java对象的序列化和反序列化工具类
            template.setValueSerializer(new RedisSerialUtil());
            return template;
        }
    }
}
