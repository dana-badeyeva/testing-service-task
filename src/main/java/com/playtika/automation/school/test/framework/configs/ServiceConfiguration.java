package com.playtika.automation.school.test.framework.configs;

import com.playtika.automation.school.test.framework.actions.ServiceActions;
import com.playtika.automation.school.test.framework.clients.ServiceFeignClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = ServiceFeignClient.class)
public class ServiceConfiguration {

    @Bean
    public ServiceActions serviceActions(ServiceFeignClient serviceFeignClient) {
        return new ServiceActions(serviceFeignClient);
    }
}
