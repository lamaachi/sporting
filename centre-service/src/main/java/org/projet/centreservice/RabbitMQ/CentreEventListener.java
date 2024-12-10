package org.projet.centreservice.RabbitMQ;

import lombok.extern.slf4j.Slf4j;
import org.projet.centreservice.dtos.CentreEvent;
import org.projet.centreservice.dtos.CentreSportifDTO;
import org.projet.centreservice.dtos.TerrainAssignmentEvent;
import org.projet.centreservice.entities.CentreSportif;
import org.projet.centreservice.repositories.CentreSportifRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lamaachi
 **/
@Service
@Slf4j
public class CentreEventListener {
    @Autowired
    private CentreSportifRepository centreSportifRepository;

    // Listen for the ASSIGN_CENTRE_EVENT event
    @RabbitListener(queues = "terrain.Queue")
    public void handleCentreEvent(CentreEvent event) {
        // Check if it's the 'ASSIGN_CENTRE_EVENT' event
        if ("ASSIGN_CENTRE_EVENT".equals(event.getEventType())) {
            // Get the event data (cast it to TerrainAssignmentEvent)
            TerrainAssignmentEvent assignmentEvent = (TerrainAssignmentEvent) event.getData();
            int centreId = assignmentEvent.getCentreId();
            int terrainId = assignmentEvent.getTerrainId();
            System.out.println(centreId+"-------------------------------"+terrainId);
            // Retrieve the CentreSportif entity by its ID
            CentreSportif centre = centreSportifRepository.findById(centreId).orElse(null);

            if (centre != null) {
                // Update the assigned terrains list (add the new terrain ID)
                if (!centre.getAssignedTerrains().contains(terrainId)) {
                    centre.getAssignedTerrains().add(terrainId);
                }

                // Save the updated CentreSportif entity
                centreSportifRepository.save(centre);
                log.info("Centre updated with new terrain: " + terrainId);
            } else {
                log.error("Centre not found with ID: " + centreId);
            }
        }
    }
}
