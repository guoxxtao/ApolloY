package com.sunlands.apolloy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.sunlands.apolloy.dao")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ApolloyWarApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApolloyWarApplication.class, args);
	}

}
