package com.hrms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类，用于全局的Web应用配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加跨域请求的映射
     *
     * @param registry 跨域请求的注册表
     */
/*    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有域名跨域请求
        registry.addMapping("/**")
                // 允许所有来源的跨域请求
                .allowedOrigins("*")
                // 允许的跨域请求方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许所有头部跨域请求
                .allowedHeaders("*")
                // 允许携带凭据的跨域请求
                .allowCredentials(true);
    }*/
}

