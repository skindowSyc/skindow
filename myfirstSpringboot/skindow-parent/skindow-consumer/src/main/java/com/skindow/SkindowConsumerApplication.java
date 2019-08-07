package com.skindow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@SpringBootApplication
@ImportResource(value = {"classpath:dubbo-server-consumer.xml"})
public class SkindowConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkindowConsumerApplication.class, args);
		log.info("######  consumer服务启动成功 ###### !!");
	}

}
