package com.playtika.automation.school.test.framework.pojo.requests;

import lombok.Value;
import lombok.Builder;

@Value
@Builder
public class RegistrationRequest {

    String email;
    String password;
}
