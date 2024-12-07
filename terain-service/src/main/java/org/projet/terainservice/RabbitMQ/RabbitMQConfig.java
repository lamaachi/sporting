package org.projet.terainservice.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue centreQueue() {
        return new Queue("centre.queue", true);  // Same queue name as in Centre-Service
    }
}
