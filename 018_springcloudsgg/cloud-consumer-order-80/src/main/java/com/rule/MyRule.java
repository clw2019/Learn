package com.rule;


import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MyRule extends AbstractLoadBalancerRule {

    int currentTotal = 0;  // 当前总数
    int currentIndex = 0;   // 当前索引
    int cycles = 3; // 循环次数

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        return this.choose(this.getLoadBalancer(), o);
    }

    private Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("没有负载均衡");
            return null;
        } else {
            Server server = null;

            while (server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                /************************************ start ********************************************/

                List<Server> allServers = lb.getAllServers();
                List<Server> upServers = lb.getReachableServers();

                if (allServers.size() == 0) {
                    return null;
                }

                if (currentTotal < cycles) {
                    server = upServers.get(currentIndex);
                    currentTotal++;
                } else {
                    currentTotal = 0;
                    currentIndex++;
                    if (currentIndex >= upServers.size()) {
                        currentIndex = 0;
                    }
                }



                /************************************* end *********************************************/


                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }
                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }


}
