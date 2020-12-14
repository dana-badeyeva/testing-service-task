package com.playtika.automation.school.test.framework.configs;

import feign.form.FormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Configuration
public class FromUrlEncodedClientConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    @Primary
    @Scope(SCOPE_PROTOTYPE)
    FormEncoder feignFormEncoder() {
        return new FormEncoder(new SpringEncoder(this.messageConverters));
    }
}
