package com.clw.modules.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: clw
 * @Description: 全局跨域配置
 * @Date: 2021/3/14 13:40
 */
@Slf4j
@Configuration
public class GlobalCorsConfig {

    /***
    * @Author: clw
    * @Description: 允许跨域调用的过滤器
    * @Param: []
    * @return: org.springframework.web.filter.CorsFilter
    * @Date: 2021/3/14 13:49
    */
    @Bean
    public CorsFilter corsFilter() {
        log.info("=====================[GlobalCorsConfig : corsFilter]=====================");
        CorsConfiguration corsConfig = new CorsConfiguration();
        // 允许所有域名进行跨域调用
        corsConfig.addAllowedOrigin("*");
        // 允许跨域发送Cookie
        corsConfig.setAllowCredentials(true);
        // 放行全部原始头信息
        corsConfig.addAllowedHeader("*");
        // 允许所有请求方法跨域调用
        corsConfig.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return new CorsFilter(corsConfigSource);
    }
}
