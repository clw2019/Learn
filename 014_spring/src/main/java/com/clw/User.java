package com.clw;

import org.springframework.stereotype.Component;

/**
 * @Author: clw
 * @Description:
 * @Date: 2021/3/25 21:08
 */
@Component("user3")
public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
