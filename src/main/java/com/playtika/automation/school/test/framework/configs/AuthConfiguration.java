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
//
//    private static final String CLIENT_ID = "test";
//    private static final String GRANT_TYPE = "password";
//    private static final String CLIENT_SECRET = "secret";
//    private static final String AUTHORIZATION = "Basic dgVzdDpzZWNyZXQ=";

    @Bean
    public AuthActions authActions(AuthFeignClient authFeignClient,
                                   @Value("Basic dGVzdDpzZWNyZXQ=") String authorization) {
        return new AuthActions(authFeignClient, authorization);
    }
}

