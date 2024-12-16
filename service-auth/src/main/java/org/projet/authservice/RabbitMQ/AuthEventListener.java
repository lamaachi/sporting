package org.projet.authservice.RabbitMQ;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class AuthEventListener {
    private final ObjectMapper objectMapper;

    public AuthEventListener() {
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "auth.admin.queue") // Define the queue to listen to
    public void handleAdminAuthEvent(String message) {
        System.out.println("Received Admin Auth Event: " + message);
    }

    @RabbitListener(queues = "new.reservation.queue")
    public void handleNewReservationMessage(String message) {
        try {

            System.out.println("[New Reservation Queue] Message received: " + message);

        } catch (Exception e) {
            System.err.println("Error processing new reservation message: " + e.getMessage());
        }
    }

}
