package org.projet.paimentservice.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventProducer {

    private final RabbitTemplate rabbitTemplate;

    // Constructor
    public PaymentEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        // Set the Jackson2JsonMessageConverter on the RabbitTemplate
        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }


    // Publish list of terrains (converted to JSON)
    public void publish(String routingKey, String event) {
        rabbitTemplate.convertAndSend("payment.events.exchange", routingKey, event);
    }
}
