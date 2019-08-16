/**
 * 
 */
package com.alumniassociation.api.common.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyan
 * @date:   2018年11月14日 下午6:37:56 
 */
@Configuration
public class TomcatPostConfig {
	@Value("${spring.server.MaxFileSize}")
    private String MaxFileSize;
    @Value("${spring.server.MaxRequestSize}")
    private String MaxRequestSize;
    @Value("${com.alumniassociation.file_temp_dir}")
    private String fileTempDir;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(MaxFileSize); // KB,MB
        // 总上传数据大小
        factory.setMaxRequestSize(MaxRequestSize);
        //设置文件上传的临时目录
        factory.setLocation(fileTempDir);
        return factory.createMultipartConfig();
    }
}
