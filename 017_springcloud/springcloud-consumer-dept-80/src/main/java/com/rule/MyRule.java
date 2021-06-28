package com.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 自定义负载均衡算法, 不能主启动类DepartConsumerApplication_80 扫描到，否则会被覆盖，所有的都会用同一个负载均衡算法
 */
public class MyRule extends AbstractLoadBalancerRule {

    // 每个服务执行5次
    private int total = 0;
    private int currentIndex = 0;

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers(); // 获得活着的服务
                List<Server> allList = lb.getAllServers();  // 获得所有的服务
                int serverCount = allList.size();   // 所有的服务总述
                if (serverCount == 0) {
                    return null;
                }

//                int index = this.chooseRandomInt(serverCount);  // 生成一个随机数
//                server = (Server)upList.get(index);  //获得随机的服务

                /******************************************************************************************************/
                /****************************************自定义负载均衡规则**********************************************/
                /******************************************************************************************************/

                if (total < 5) {
                    server = upList.get(currentIndex);
                    total++;
                } else {
                    total = 0;
                    currentIndex++;
                    if (currentIndex >= upList.size()) {
                        currentIndex = 0;
                    }
//                    server = upList.get(currentIndex);
                 }


                /******************************************************************************************************/
                /******************************************************************************************************/
                /******************************************************************************************************/
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

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
