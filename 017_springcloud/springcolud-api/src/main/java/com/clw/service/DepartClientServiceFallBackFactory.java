package com.clw.service;

import com.clw.entity.Depart;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartClientServiceFallBackFactory implements FallbackFactory<IFeignClientService> {
    @Override
    public IFeignClientService create(Throwable cause) {
        return new IFeignClientService() {
            @Override
            public List<Depart> list() {
                return null;
            }

            @Override
            public Depart queryById(String deptNo) {
                return new Depart()
                        .setDeptNo("deptNo => " + deptNo + "没有对应的信息，客户端提供了降级的信息，这个服务已经被关闭了")
                        .setDbName("没有对应的数据。。。");
            }

            @Override
            public void addDept(Depart depart) {

            }
        };
    }
}
