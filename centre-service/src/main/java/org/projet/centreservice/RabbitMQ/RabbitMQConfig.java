package org.projet.centreservice.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {



    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange centreExchange() {
        return new DirectExchange("event_centre_exchange", true, false);
    }

    @Bean
    public Queue terrainQueue() {
        return new Queue("centre.Queue", true);
    }

    @Bean
    public Binding binding(DirectExchange terrainExchange, Queue terrainQueue) {
        return BindingBuilder.bind(terrainQueue)
                .to(terrainExchange)
                .with("centre_routing_key");
    }
}

