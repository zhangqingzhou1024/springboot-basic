package com.liziyuan.hope.common.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-04-16 21:45
 */
public class SpringEventTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.liziyuan.hope.common.event");
        CustomSpringEventPublisher customSpringEventPublisher =  applicationContext.getBean(CustomSpringEventPublisher.class);

        customSpringEventPublisher.doStuffAndPublishAnEvent("test");
    }
}
