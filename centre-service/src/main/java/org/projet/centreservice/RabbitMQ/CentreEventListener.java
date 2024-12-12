package org.projet.centreservice.RabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.projet.centreservice.dtos.CentreEvent;
import org.projet.centreservice.dtos.TerrainAssignmentEvent;
import org.projet.centreservice.entities.CentreSportif;
import org.projet.centreservice.repositories.CentreSportifRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CentreEventListener {

    private final CentreSportifRepository centreSportifRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CentreEventListener(CentreSportifRepository centreSportifRepository, ObjectMapper objectMapper) {
        this.centreSportifRepository = centreSportifRepository;
        this.objectMapper = objectMapper;
    }


    @RabbitListener(queues = "assign.terrain.queue")
    public void handleAssignCentreEvent(String eventMessage) {
        log.info("Received event message: {}", eventMessage);

        try {
            // Deserialize the JSON message to TerrainAssignmentEvent
            TerrainAssignmentEvent assignmentEvent = objectMapper.readValue(eventMessage, TerrainAssignmentEvent.class);

            int centreId = assignmentEvent.getCentreId();
            int terrainId = assignmentEvent.getTerrainId();

            // Retrieve the CentreSportif from the repository
            CentreSportif centre = centreSportifRepository.findById(centreId)
                    .orElseThrow(() -> new IllegalArgumentException("Centre not found with ID: " + centreId));

            // Check and update the assignment
            if (!centre.getAssignedTerrains().contains(terrainId)) {
                centre.getAssignedTerrains().add(terrainId);
                centreSportifRepository.save(centre);
                log.info("Terrain with ID={} successfully assigned to Centre with ID={}", terrainId, centreId);
            } else {
                log.info("Terrain with ID={} is already assigned to Centre with ID={}", terrainId, centreId);
            }
        } catch (Exception e) {
            log.error("Error processing ASSIGN_CENTRE_EVENT: {}", e.getMessage(), e);
        }
    }
}
