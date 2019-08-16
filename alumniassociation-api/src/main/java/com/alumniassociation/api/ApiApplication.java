package com.alumniassociation.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.alumniassociation.api.controller")
// mapper 接口类扫描包配置
@MapperScan({"com.alumniassociation.*.dao", "com.alumniassociation.*.*.dao", "com.alumniassociation.*.resitory"})
@ComponentScan("com.alumniassociation.*.service")
@ComponentScan("com.alumniassociation.*.*.service")
@ComponentScan("com.alumniassociation.api")
@ComponentScan("com.alumniassociation.common")
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
