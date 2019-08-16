package com.alumniassociation.api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池的创建
 * @author wangyan
 * @date:   2018年11月26日 下午3:51:51
 */
@Configuration
public class DIConfig {

    private static final int kPoolLimit = 5000;

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(kPoolLimit);
    }

}

