package com.alumniassociation.api.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @date 2017-07-10 22:12:31
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	private Logger logger = LoggerFactory.getLogger(Swagger2.class);

	
	@Bean
    public Docket createRestApi() {
        logger.info("SwaggerConfig createRestApi 构建REST API");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alumniassociation.api.controller")) //Controller所在包(必须新建包)
                .paths(PathSelectors.any())
                .build();
    }
	
    private ApiInfo apiInfo() {
        logger.info("SwaggerConfig apiInfo API信息");
        return new ApiInfoBuilder()
                .title("校友会公众号API接口文档")  //标题
                .description("swagger description")  //描述
                .version("1.0")
                .build();
    }
}
