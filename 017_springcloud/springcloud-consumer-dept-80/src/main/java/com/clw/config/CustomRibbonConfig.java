package com.clw.config;
import com.rule.MyRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRibbonConfig {


    @Bean
    public IRule myRule() {
//        return new RoundRobinRule();
        return new MyRule();
    }
}
