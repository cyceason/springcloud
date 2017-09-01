package com.cyc.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cyc_e on 2017/9/2.
 */
@Configuration
public class ConditionConfig {

    /**
     * 当WindowsCondition条件满足时，创建WindowsListServer bean。
     *
     * @return
     */
    @Bean
    @Conditional(WindowsCondition.class)
    public ListServer windowsListServer() {
        return new WindowsListServer();
    }

    /**
     * 当LinuxCondition条件满足时，创建LinuxListServer bean。
     *
     * @return
     */
    @Bean
    @Conditional(LinuxCondition.class)
    public ListServer linuxCondition() {
        return new LinuxListServer();
    }
}
