package com.loja.autos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue myQueue() {
        return new Queue("myQueue", false);
    }
}
