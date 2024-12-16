package org.projet.authservice.RabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
    public DirectExchange authEventsExchange() {
        return new DirectExchange("auth.events.exchange",true,false);
    }

    @Bean
    public Queue adminAuthQueue() {
        return new Queue("auth.admin.queue");
    }

    @Bean
    public Binding adminAuthBinding(Queue adminAuthQueue, DirectExchange authExchange) {
        return BindingBuilder.bind(adminAuthQueue).to(authExchange).with("auth.admin");
    }

    @Bean
    public Queue allTerrainQueue() {
        return new Queue("all.terrain.queue", true);
    }

    @Bean
    public Binding bindAllTerrainQueue(Queue allTerrainQueue, DirectExchange terrainEventsExchange) {
        return BindingBuilder.
                bind(allTerrainQueue).
                to(terrainEventsExchange).
                with("terrain.all");
    }


    @Bean
    public Queue reservationQueue() {
        return new Queue("new.reservation.queue", true);
    }

    @Bean
    public Binding bindReservationQueue(Queue reservationQueue, DirectExchange reservationExchange) {
        return BindingBuilder
                .bind(reservationQueue)
                .to(reservationExchange)
                .with("reservation.new");
    }

}
