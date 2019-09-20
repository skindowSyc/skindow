package com.skindow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@SpringBootApplication
@ImportResource(value = {"classpath:dubbo-server-backstage-consumer.xml"})
public class SkindowBackstageApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SkindowBackstageApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
		log.info("######  backstage服务启动成功 ###### !!");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(SkindowBackstageApplication.class);
	}
}
