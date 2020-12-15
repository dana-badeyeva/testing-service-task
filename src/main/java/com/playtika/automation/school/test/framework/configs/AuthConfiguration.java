package com.playtika.automation.school.test.framework.configs;

import com.playtika.automation.school.test.framework.actions.AuthActions;
import com.playtika.automation.school.test.framework.clients.AuthFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@EnableFeignClients(clients = AuthFeignClient.class)
public class AuthConfiguration {

    @Bean
    public AuthActions authActions(AuthFeignClient authFeignClient,
                                   @Value("${auth.token}") String authorization) {
        return new AuthActions(authFeignClient, authorization);
    }
}

