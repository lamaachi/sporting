package org.projet.centreservice.RabbitMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.projet.centreservice.dtos.TerrainAssignmentEvent;
import org.projet.centreservice.dtos.TerrainDTO;
import org.projet.centreservice.entities.CentreSportif;
import org.projet.centreservice.repositories.CentreSportifRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CentreEventListener {

    private final CentreSportifRepository centreSportifRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public CentreEventListener(CentreSportifRepository centreSportifRepository) {
        this.centreSportifRepository = centreSportifRepository;
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "assign.terrain.queue")
    public void handleTerrainAssignEvent(String message) {
        try {
            // Parse the message as a JSON object
            JsonNode jsonNode = objectMapper.readTree(message);

            // Extract the terrainId and centreId fields
            int terrainId = jsonNode.get("terrainId").asInt();
            int centreId = jsonNode.get("centreId").asInt();

            // Log the extracted data
            System.out.println("Terrain ID: " + terrainId + ", Centre ID: " + centreId);

            // Retrieve the CentreSportif by ID
            CentreSportif centreSportif = centreSportifRepository.findById(centreId).orElseThrow(() ->
                    new RuntimeException("CentreSportif not found with ID: " + centreId));

            // Check if the terrainId is already in the assignedTerrains list
            if (!centreSportif.getAssignedTerrains().contains(terrainId)) {
                // Add the terrainId if not already present
                centreSportif.getAssignedTerrains().add(terrainId);
                // Save the updated CentreSportif entity
                centreSportifRepository.save(centreSportif);
                System.out.println("Added terrainId " + terrainId + " to CentreSportif ID " + centreId);
            } else {
                System.out.println("Terrain ID " + terrainId + " is already assigned to CentreSportif ID " + centreId);
            }

        } catch (Exception e) {
            // Handle parsing or processing errors
            System.err.println("Failed to process message: " + message);
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "all.terrain.queue")
    public void handleAllTerrainsEvent(String message) {
        try {
            // Parse the message as a JSON object
            JsonNode jsonNode = objectMapper.readTree(message);
            // Process the extracted data
            System.out.println("All Terain:"+message);

        } catch (Exception e) {
        }
    }

    // Listen to RabbitMQ queue for "add.new.terrain.queue"
    @RabbitListener(queues = "add.new.terrain.queue")
    public void handleAddTerrainEvent(String message) {
        try {
            // Deserialize the incoming JSON message into a TerrainDTO object
            TerrainDTO event = objectMapper.readValue(message, TerrainDTO.class);

            // Log the received JSON message (optional)
            System.out.println("Received Add Terrain Event: " + message);

            // Logic to handle a newly added terrain
            System.out.println("New terrain added: " + event.getNom());
        } catch (Exception e) {
            System.err.println("Error processing Add Terrain Event: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "update.terrain.queue")
    public void handleUpdateTerrainEvent(String message) {
        try {
            int terrainId = 0;
            // Deserialize the incoming JSON message into a JSON node
            JsonNode jsonNode = objectMapper.readTree(message);
            System.out.println("Json Node Tree :"+jsonNode);
            JsonNode terrainNode = jsonNode.get("id");
            System.out.println("TerainID"+terrainNode.asInt());
            if (terrainNode.isInt()) {
                terrainId = terrainNode.asInt();
                // Proceed with using terrainId
                int newCentreId = jsonNode.get("centreId").asInt();
                System.out.println("New Centre ID"+terrainNode.asInt());
                // Log the received message (optional)
                System.out.println("Received Update Terrain Event: " + message);

                // Remove terrainId from the assignedTerrains list of all centres except the one with the new centreId
                Iterable<CentreSportif> centres = centreSportifRepository.findAll();
                for (CentreSportif centre : centres) {
                    if (centre.getId() != newCentreId) {
                        // Remove the terrainId from the centre's assignedTerrains if it's present
                        if (centre.getAssignedTerrains().contains(terrainId)) {
                            centre.getAssignedTerrains().remove(Integer.valueOf(terrainId));
                            centreSportifRepository.save(centre);
                            System.out.println("Removed terrain ID " + terrainId + " from CentreSportif ID " + centre.getId());
                        }
                    }
                }

                // Now update the new centre's assignment
                CentreSportif newCentre = centreSportifRepository.findById(newCentreId)
                        .orElseThrow(() -> new RuntimeException("CentreSportif not found with ID: " + newCentreId));

                // Add the terrainId to the new centre's assignedTerrains list if not already present
                if (!newCentre.getAssignedTerrains().contains(terrainId)) {
                    newCentre.getAssignedTerrains().add(terrainId);
                    centreSportifRepository.save(newCentre);
                    System.out.println("Assigned terrain ID " + terrainId + " to CentreSportif ID " + newCentreId);
                }
            } else {
                // Handle the case where the terrainId is missing or not an integer
                System.out.println("Terrain ID is missing or invalid.");

            }

        } catch (Exception e) {
            // Handle parsing or processing errors
            System.err.println("Error processing Update Terrain Event: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
