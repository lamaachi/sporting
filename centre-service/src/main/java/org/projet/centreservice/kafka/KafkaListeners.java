package org.projet.centreservice.kafka;

import org.projet.centreservice.dtos.TerrainAssignmentEvent;
import org.projet.centreservice.services.CentreService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author lamaachi
 **/
@Component
public class KafkaListeners {
    private final CentreService centreSportifService;

    public KafkaListeners(CentreService centreSportifService) {
        this.centreSportifService = centreSportifService;
    }

    // This listener will react to any message published on the 'terrain-assignment-topic'
    @KafkaListener(topics = "terrain-assignment-topic", groupId = "groupId")
    void listener(TerrainAssignmentEvent event) {
        System.out.println("Received Event: " + event);

        // Call the service to assign the terrain to the centre
        centreSportifService.assignTerrainToCentre(event);
    }
}


