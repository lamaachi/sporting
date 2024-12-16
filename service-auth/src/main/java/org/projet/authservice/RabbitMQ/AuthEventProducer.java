package org.projet.authservice.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class AuthEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public AuthEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    public void sendAdminAuthMessage(String message) {
        String exchange = "auth.events.exchange"; // Define your exchange name
        String routingKey = "auth.admin"; // Define your routing key

        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
