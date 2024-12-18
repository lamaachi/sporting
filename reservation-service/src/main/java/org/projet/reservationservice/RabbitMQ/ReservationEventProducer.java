//package org.projet.reservationservice.RabbitMQ;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ReservationEventProducer {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    // Constructor
//    public ReservationEventProducer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//        // Set the Jackson2JsonMessageConverter on the RabbitTemplate
//        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//    }
//
//
//
//
//}
