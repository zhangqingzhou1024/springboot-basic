package com.liziyuan.hope.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * 事件
 * 自定义 spring event
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-16 14:06
 */
public class CustomSpringEvent extends ApplicationEvent {
    private String message;

    public CustomSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
