package org.projet.centreservice.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public TopicExchange centreExchange() {
        return new TopicExchange("centre.exchange");
    }

    @Bean
    public Queue centreQueue() {
        return new Queue("centre.queue", true);
    }

    @Bean
    public Binding binding(Queue centreQueue, TopicExchange centreExchange) {
        return BindingBuilder
                .bind(centreQueue)
                .to(centreExchange)
                .with("centre.created");
    }
}

