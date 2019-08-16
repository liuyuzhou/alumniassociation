package com.alumniassociation.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan({"com.alumniassociation.web.common.dao", "com.alumniassociation.*.dao", "com.alumniassociation.*.*.dao"})
@ComponentScan("com.alumniassociation.*.service")
@ComponentScan("com.alumniassociation.*.*.service")
@ComponentScan("com.alumniassociation.*.*.rdao")
@ComponentScan("com.alumniassociation.web.common")
@ComponentScan("com.alumniassociation.common")
@ComponentScan("com.alumniassociation.message")
@EnableScheduling
public class CyFastApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyFastApplication.class, args);
	}
	
}
