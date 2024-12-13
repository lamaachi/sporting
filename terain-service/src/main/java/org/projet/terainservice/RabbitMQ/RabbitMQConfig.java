package org.projet.terainservice.RabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }


    @Bean
    public DirectExchange terrainEventsExchange() {
        return new DirectExchange("terrain.events",true,false);
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
    public Queue updateTerrainQueue() {
        return new Queue("update.terrain.queue", true);
    }

    @Bean
    public Binding bindAllTerrainQueue(Queue allTerrainQueue, DirectExchange terrainEventsExchange) {
        return BindingBuilder.
                bind(allTerrainQueue).
                to(terrainEventsExchange).
                with("terrain.all");
    }

    @Bean
    public Binding bindAddNewTerrainQueue(Queue addNewTerrainQueue, DirectExchange terrainEventsExchange) {
        return BindingBuilder.
                bind(addNewTerrainQueue).
                to(terrainEventsExchange).
                with("terrain.add");
    }

    @Bean
    public Binding bindAssignTerrainQueue(Queue assignTerrainQueue, DirectExchange terrainEventsExchange) {
        return BindingBuilder
                .bind(assignTerrainQueue)
                .to(terrainEventsExchange)
                .with("terrain.assign");
    }

    @Bean
    public Binding bindUpdateTerrainQueue(Queue assignTerrainQueue, DirectExchange terrainEventsExchange) {
        return BindingBuilder
                .bind(updateTerrainQueue())
                .to(terrainEventsExchange)
                .with("terrain.update");
    }
}
