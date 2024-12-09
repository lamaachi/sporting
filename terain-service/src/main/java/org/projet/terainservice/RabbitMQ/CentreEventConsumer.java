package org.projet.terainservice.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CentreEventConsumer {

//    @RabbitListener(queues = "centre.queue")
//    public void consumeCentreEvent(String message) {
//        System.out.println("Received Centre Event: " + message);
//        // Handle event (e.g., update terrain-related data)
//    }
}
