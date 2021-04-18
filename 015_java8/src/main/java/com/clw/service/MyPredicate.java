package com.clw.service;

public interface MyPredicate <T>{

    boolean test(T t);

    // JDK 1.8 之后 接口可以有方法体， 需要添加 default
    default boolean test2(T t) {
        return true;
    }
}
