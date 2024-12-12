package org.projet.terainservice.RabbitMQ;

import org.springframework.amqp.core.*;
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
    public TopicExchange terrainEventsExchange() {
        return new TopicExchange("terrain.events");
    }


    @Bean
    public Queue allTerrainQueue() {
        return new Queue("all.terrain.queue", true);
    }

    @Bean
    public Queue addNewTerrainQueue() {
        return new Queue("add.new.terrain.queue", true);
    }

    @Bean
    public Queue assignTerrainQueue() {
        return new Queue("assign.terrain.queue", true);
    }

    @Bean
    public Binding bindAllTerrainQueue(Queue allTerrainQueue, TopicExchange terrainEventsExchange) {
        return BindingBuilder.
                bind(allTerrainQueue).
                to(terrainEventsExchange).
                with("terrain.all");
    }

    @Bean
    public Binding bindAddNewTerrainQueue(Queue addNewTerrainQueue, TopicExchange terrainEventsExchange) {
        return BindingBuilder.
                bind(addNewTerrainQueue).
                to(terrainEventsExchange).
                with("terrain.add");
    }

    @Bean
    public Binding bindAssignTerrainQueue(Queue assignTerrainQueue, TopicExchange terrainEventsExchange) {
        return BindingBuilder
                .bind(assignTerrainQueue)
                .to(terrainEventsExchange)
                .with("terrain.assign");
    }
}
