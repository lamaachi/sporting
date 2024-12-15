//package org.projet.centreservice.RabbitMQ;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
////import org.projet.centreservice.dtos.TerrainAssignmentEvent;
//import org.projet.centreservice.dtos.TerrainDTO;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TerrainEventListener {
//    private final ObjectMapper objectMapper;
//
//    public TerrainEventListener() {
//        this.objectMapper = new ObjectMapper();
//    }
//
//    @RabbitListener(queues = "all.terrain.queue")
//    public void handleAllTerrainsEvent(List<TerrainDTO> event) {
//        try {
//
//            System.out.println("Received All Terrains Event: " + event);
//        } catch (Exception e) {
//            System.err.println("Error converting event to JSON: " + e.getMessage());
//        }
//    }
//
//
//    @RabbitListener(queues = "assign.terrain.queue")
//    public void handleTerrainAssignmentEvent(TerrainAssignmentEvent event) {
//        try {
//            String json = objectMapper.writeValueAsString(event);
//            System.out.println("Received Terrain Assignment Event: " + json);
//        } catch (Exception e) {
//            System.err.println("Error converting event to JSON: " + e.getMessage());
//        }
//    }
//
//    @RabbitListener(queues = "add.new.terrain.queue")
//    public void handleAddTerrainEvent() {
//        try {
//            System.out.println("Received Add Terrain Event");
//        } catch (Exception e) {
//            System.err.println("Error converting event to JSON: " + e.getMessage());
//        }
//    }
//}
