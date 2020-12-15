package com.playtika.automation.school.test.framework.clients;

import com.playtika.automation.school.test.framework.configs.FromUrlEncodedClientConfiguration;
import com.playtika.automation.school.test.framework.pojo.requests.AuthenticationRequest;
import com.playtika.automation.school.test.framework.pojo.requests.RegistrationRequest;
import com.playtika.automation.school.test.framework.pojo.responses.LoginResponse;
import com.playtika.automation.school.test.framework.pojo.responses.RegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "login-client",
        url = "${test.service.host}",
        configuration = FromUrlEncodedClientConfiguration.class
)
public interface AuthFeignClient {

    @PostMapping(value = "/v1/accounts", consumes = "application/json")
    RegistrationResponse register(@RequestBody RegistrationRequest registrationRequest);

    @PostMapping(value = "/oauth/token", consumes = "application/x-www-form-urlencoded")
    LoginResponse login(@RequestHeader("Authorization") String authorization,
                        @RequestParam("grant_type") String grantType,
                        @RequestParam("username") String email,
                        @RequestParam("password") String password,
                        @RequestBody AuthenticationRequest authenticationRequest);
}