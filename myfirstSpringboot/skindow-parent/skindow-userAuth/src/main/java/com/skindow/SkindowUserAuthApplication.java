package com.skindow;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * @author: hxy
 * @description: SpringBoot启动类
 * @date: 2017/10/24 11:55
 */
@SpringBootApplication
@ImportResource(value = {"classpath:spring-skindow-auth-provider.xml"})
@MapperScan("com.skindow.dao")
@Slf4j
public class SkindowUserAuthApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SkindowUserAuthApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
		log.info("###### skindow服务auth dubbo服务启动成功 ###### !!");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(SkindowUserAuthApplication.class);
	}
}
