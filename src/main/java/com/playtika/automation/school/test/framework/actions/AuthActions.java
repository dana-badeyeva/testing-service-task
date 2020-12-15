package com.playtika.automation.school.test.framework.actions;

import com.playtika.automation.school.test.framework.clients.AuthFeignClient;
import com.playtika.automation.school.test.framework.pojo.requests.AuthenticationRequest;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.responses.LoginResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthActions {

    private final AuthFeignClient authFeignClient;
    private final String authorization;

    public void register(String email, String password) {
        RegistrationRequest request = RegistrationRequest.builder()
                .email(email)
                .password(password)
                .build();
        authFeignClient.register(request);
    }

    public LoginResponse login(String email, String password, String grantType, String scope) {
        AuthenticationRequest request = AuthenticationRequest.builder()
                .scope(scope)
                .build();
        return authFeignClient.login(authorization, grantType, email, password, request);
    }
}