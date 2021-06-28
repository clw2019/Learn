package com.clw.kafka;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.alibaba.fastjson.JSONObject;
import com.clw.vo.SendMessageVO;

public class KafkaAppender extends UnsynchronizedAppenderBase<LoggingEvent> {

    protected void append(LoggingEvent event) {
        String loggerName = event.getLoggerName();
        if (loggerName.equals("com.clw.aop.LogAspect")) {
            System.out.println("event : " + event);
//            SendMessageVO sendMessageVO = JSONObject.parseObject(event.getMessage(), SendMessageVO.class);
            KafkaConfig kafkaConfig = new KafkaConfig();
//            kafkaConfig.kafkaTemplate().send("common-logs", event.getMessage());
        }

    }
}
