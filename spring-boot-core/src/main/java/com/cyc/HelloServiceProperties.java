package com.cyc;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hello") // 在配置文件中的前缀
public class HelloServiceProperties {

    private static final String MSG = "world";
    private String msg = MSG; // 默认hello.msg=world

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
