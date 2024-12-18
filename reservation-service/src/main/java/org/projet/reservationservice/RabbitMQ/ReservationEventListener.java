//package org.projet.reservationservice.RabbitMQ;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ReservationEventListener {
//
//    private final ObjectMapper objectMapper;
//
//    public ReservationEventListener(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @RabbitListener(queues = "new.reservation.queue")
//    public void handleNewReservationMessage(String message) {
//        try {
//
//            System.out.println("[New Reservation Queue] Message received: " + message);
//
//        } catch (Exception e) {
//            System.err.println("Error processing new reservation message: " + e.getMessage());
//        }
//    }
//
//    @RabbitListener(queues = "all.terrain.queue")
//    public void handleAllTerrainMessage(String message) {
//        try {
//            System.out.println("[All Terrain Queue] Message received: " + message);
//
//        } catch (Exception e) {
//            System.err.println("Error processing terrain message: " + e.getMessage());
//        }
//    }
//
//}
