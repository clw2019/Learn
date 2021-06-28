package com.clw.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     * RoundRobinRule: 轮询（默认）
     * RandomRule: 随机
     * AvailabilityFilteringRule：先过滤掉多次访问故障而处于短路器跳闸状态的服务，
     *                            和过滤并发的连接数量超过阈值的服务，然后对剩余的服务进行RoundRobinRule（轮询）策略
     * WeightedResponseTimeRule：根据平均响应时间计算所欲服务的权重，响应时间越快服务权重越大，容易命中的概率越高。
     *                          刚启动时，如果统计信息不中，则使用RoundRobinRule（轮询）策略，等统计到足够的信息，就会自动切换到WeightedResponseTimeRule
     *
     * RetryRule：先按照RoundRobinRule（轮询）的策略获取服务，如果获取的服务失败就会在指定的时间内重试，选取可用的服务。若多获取失败，就不会再次获取改服务
     * BestAvailableRule：先过滤掉多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务。
     * ZoneAvoidanceRule：默认规则，复合判断Server所在区域的性能和Server的可用性选择服务器
     *
     * AbstractLoadBalancerRule
     * ClientConfigEnabledRoundRobinRule
     * PredicateBasedRule
     * ResponseTimeWeightedRule
     */
    @Bean
    @LoadBalanced //配置负载均衡实现 RestTemplate (Ribbon)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*@Bean
    @LoadBalanced //配置负载均衡实现 RestTemplate (Ribbon)
    public RestOperations restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }*/
}
