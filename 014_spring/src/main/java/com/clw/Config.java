package com.clw;

import org.springframework.context.annotation.Bean;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/3/25 21:46
 */
public class Config {

    @Bean
    public User getUser() {
        return new User();
    }
}
