package com.lifebook.UserService.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriptionConfiguration {

    private static final String SUBSCRIPTION_EXCHANGE = "subscription";

    @Bean(name = "subscriptionExchange")
    public TopicExchange subscriptionExchange() {
        return new TopicExchange(SUBSCRIPTION_EXCHANGE);
    }

}
