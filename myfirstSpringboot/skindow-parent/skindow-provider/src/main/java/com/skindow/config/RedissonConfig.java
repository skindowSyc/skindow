package com.skindow.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 10:45 2021/4/2
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
@Configurable
@Slf4j
@Component
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient() throws IOException {
        log.info("aaaaa ========================");
        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("reddision-config.yml"));
        return Redisson.create(config);
    }
}
