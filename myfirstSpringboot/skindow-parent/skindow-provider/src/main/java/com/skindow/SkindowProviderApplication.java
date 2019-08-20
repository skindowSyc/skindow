package com.skindow;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
@Slf4j
@SpringBootApplication
@ImportResource(value = {"classpath:spring-skindow-provider.xml"})
@MapperScan("com.skindow.mapper")
public class SkindowProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(SkindowProviderApplication.class, args);
		log.info("###### skindow服务provider dubbo服务启动成功 ###### !!");
	}

}
