package org.projet.paimentservice.RabbitMQ;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class PaymentEventListener {
    private final ObjectMapper objectMapper;

    public PaymentEventListener() {
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "new.reservation.queue")
    public void handleNewReservationMessage(String message) {
        try {

            System.out.println("[New Reservation Queue] Message received: " + message);

        } catch (Exception e) {
            System.err.println("Error processing new reservation message: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "new.payment.queue")
    public void handlePaymentMessage(String message) {
        try {

            System.out.println("[New Payment Queue] Message received: " + message);

        } catch (Exception e) {
            System.err.println("Error processing new Payment message: " + e.getMessage());
        }
    }
}
