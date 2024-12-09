package org.projet.terainservice.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public DirectExchange terrainExchange() {
        return new DirectExchange("event_terrain_exchange", true, false);
    }

    @Bean
    public Queue terrainQueue() {
        return new Queue("terrain.Queue", true);
    }

    @Bean
    public Binding binding(DirectExchange terrainExchange, Queue terrainQueue) {
        return BindingBuilder.bind(terrainQueue)
                .to(terrainExchange)
                .with("terrain_routing_key");
    }
}
