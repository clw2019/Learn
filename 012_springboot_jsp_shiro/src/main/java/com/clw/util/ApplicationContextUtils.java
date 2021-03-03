package com.clw.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/2/28 14:20
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /***
    * @Author: clw
    * @Description: 根据beanName获取Spring工厂中的bean对象
    * @Param: [beanName]
    * @return: java.lang.Object
    * @Date: 2021/2/28 14:28
    */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
