package com.client.management.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApplicationConfig {

    @Value("${api.kafka.topic.name}")
    private String topicName;

    @Value("${api.kafka.message.region}")
    private String region;

    @Value("${api.kafka.message.code.success}")
    private String statusCodeSuccess;

    @Value("${api.kafka.message.code.error}")
    private String statusCodeError;

    @Value("${api.kafka.message.code.post}")
    private String statusCodePost;

    @Value("${api.kafka.message.code.get}")
    private String statusCodeGet;
}