package com.clw.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringBootLogbackTest {

    public static final Logger logger = LoggerFactory.getLogger(SpringBootLogbackTest.class);

    @Test
    public void test() {
        // 日志输出
//        for (int i = 0; i < 10000; i++) {
            logger.error("error");
            logger.warn("warn");
            logger.info("info");
            logger.debug("debug");
            logger.trace("trace");
//        }
    }
}
