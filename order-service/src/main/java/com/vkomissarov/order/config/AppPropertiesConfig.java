package com.vkomissarov.order.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class AppPropertiesConfig {

    @Value("${retry.period:3000}")
    private int period;

    @Value("${retry.maxPeriod:30000}")
    private int maxPeriod;

    @Value("${retry.maxAttempts:5}")
    private int maxAttempts;

    @Value("${feign.order.basePath}")
    private String orderUrl;

    @Value("${feign.order.customersUrl}")
    private String customersUrl;

    @Value("${spring.application.name}")
    private String appName;

}
