package org.projet.centreservice.controllers;

import org.projet.centreservice.dtos.CentreSportifDTO;
//import org.projet.centreservice.dtos.TerrainAssignmentEvent;
import org.projet.centreservice.entities.CentreSportif;
import org.projet.centreservice.services.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing CentreSportif entities.
 * Provides endpoints for CRUD operations.
 *  Test
 * @author lamaachi
 */

@RestController
@RequestMapping("/api/centres")
public class CentreController {

    private CentreService centreSportifService;
//    private final KafkaTemplate<String, TerrainAssignmentEvent> kafkaTemplate;

    public CentreController(CentreService centreService) {
        this.centreSportifService = centreService;
    }

    // Create a new CentreSportif
    @PostMapping
    public ResponseEntity<CentreSportif> createCentreSportif(@RequestBody CentreSportifDTO centreSportifDTO) {
        CentreSportif createdCentre = centreSportifService.createCentreSportif(centreSportifDTO);
        return new ResponseEntity<CentreSportif>(createdCentre, HttpStatus.CREATED);
    }

    // Get all CentreSportifs
    @GetMapping
    public List<CentreSportif> getAllCentreSportifs() {
        return centreSportifService.getAllCentreSportifs();
    }

    // Get CentreSportif by ID
    @GetMapping("/{id}")
    public ResponseEntity<CentreSportif> getCentreSportifById(@PathVariable int id) {
        CentreSportif centreSportif = centreSportifService.getCentreSportifById(id);
        if (centreSportif != null) {
            return new ResponseEntity<CentreSportif>(centreSportif, HttpStatus.OK);
        }
        return new ResponseEntity<CentreSportif>(HttpStatus.NOT_FOUND);
    }

    // Update CentreSportif by ID
    @PutMapping("/{id}")
    public ResponseEntity<CentreSportif> updateCentreSportif(@PathVariable int id, @RequestBody CentreSportifDTO centreSportifDTO) {
        CentreSportif updatedCentre = centreSportifService.updateCentreSportif(id, centreSportifDTO);
        if (updatedCentre != null) {
            return new ResponseEntity<CentreSportif>(updatedCentre, HttpStatus.OK);
        }
        return new ResponseEntity<CentreSportif>(HttpStatus.NOT_FOUND);
    }

    // Delete CentreSportif by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentreSportif(@PathVariable int id) {
        boolean isDeleted = centreSportifService.deleteCentreSportif(id);
        if (isDeleted) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping("/{centreId}/assign-terrain")
//    public ResponseEntity<String> assignTerrainToCentre(@PathVariable int centreId, @RequestBody int terrainId) {
//        try {
//            // Create the event object
//            TerrainAssignmentEvent event = new TerrainAssignmentEvent();
//            event.setCentreId(centreId);
//            event.setTerrainId(terrainId);
//
//            // Call the service to assign the terrain
////            centreSportifService.assignTerrainToCentre(event);
//
//            // Publish the event to Kafka
////            kafkaTemplate.send("terrain-assignment-topic", event);
//
//            return ResponseEntity.ok("Terrain assigned to centre and event published to Kafka.");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Error assigning terrain: " + e.getMessage());
//        }
//    }

}
