package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.Serializable;

/**
 * 
 * @author songhaozhi
 *
 */
@SpringBootApplication
@MapperScan("com.songhaozhi.mayday.mapper")
@EnableTransactionManagement
@EnableCaching
public class Application implements Serializable {
	public static void main(String[] args) { 
		SpringApplication.run(Application.class, args);
	}
}
