package org.projet.centreservice.services;

import org.projet.centreservice.RabbitMQ.CentreEventProducer;
import org.projet.centreservice.dtos.CentreSportifDTO;
import org.projet.centreservice.dtos.TerrainAssignmentEvent;
import org.projet.centreservice.entities.CentreSportif;
import org.projet.centreservice.repositories.CentreSportifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing CentreSportif operations.
 * Handles CRUD operations for CentreSportif.
 *
 * @author lamaachi
 */
@Service
public class CentreService {

    private final CentreEventProducer eventProducer;
    private CentreSportifRepository centreSportifRepository;

    public CentreService(CentreEventProducer eventProducer, CentreSportifRepository centreSportifRepository) {
        this.eventProducer = eventProducer;
        this.centreSportifRepository = centreSportifRepository;
    }


    // This method assigns a terrain to a centre
    public void assignTerrainToCentre(TerrainAssignmentEvent event) {
        // 1. Find the centre by its ID
        CentreSportif centre = centreSportifRepository.findById(event.getCentreId())
                .orElseThrow(() -> new RuntimeException("Centre not found"));

        // 3. Update the centre's terrain details
        // You can either add this terrain to the centre’s list of terrains or assign a reference (if one-to-many relationship)
        if (centre.getAssignedTerrains() == null) {
            // Initialize the list if it's null
            centre.setAssignedTerrains(new ArrayList<>());
        }
        // Example of adding terrain ID to centre (assuming you have a list of terrain IDs in the CentreSportif model)
        centre.getAssignedTerrains().add(event.getCentreId());

        // 4. Save the updated centre back to the database
        centreSportifRepository.save(centre);

        // Log or output the assignment
        System.out.println("Terrain " + event.getTerrainId() + " assigned to Centre " + event.getCentreId());
    }

    // Create CentreSportif
    public CentreSportif createCentreSportif(CentreSportifDTO centreSportifDTO) {
        CentreSportif centreSportif = new CentreSportif();
        centreSportif.setNom(centreSportifDTO.getNom());
        centreSportif.setAdresse(centreSportifDTO.getAdresse());
        centreSportif.setHoraires(centreSportifDTO.getHoraires());
        // Set terrains and equipements here if needed

        // Publish event
        eventProducer.sendCentreCreatedEvent(centreSportifDTO);

        return centreSportifRepository.save(centreSportif);
    }

    // Get all CentreSportifs
    public List<CentreSportif> getAllCentreSportifs() {
        return centreSportifRepository.findAll();
    }

    // Get CentreSportif by ID
    public CentreSportif getCentreSportifById(int id) {
        Optional<CentreSportif> centreSportif = centreSportifRepository.findById(id);
        return centreSportif.orElse(null);
    }

    // Update CentreSportif by ID
    public CentreSportif updateCentreSportif(int id, CentreSportifDTO centreSportifDTO) {
        Optional<CentreSportif> existingCentreSportif = centreSportifRepository.findById(id);
        if (existingCentreSportif.isPresent()) {
            CentreSportif centreSportif = existingCentreSportif.get();
            centreSportif.setNom(centreSportifDTO.getNom());
            centreSportif.setAdresse(centreSportifDTO.getAdresse());
            centreSportif.setHoraires(centreSportifDTO.getHoraires());
            // Update terrains and equipements if needed
            return centreSportifRepository.save(centreSportif);
        }
        return null;
    }

    // Delete CentreSportif by ID
    public boolean deleteCentreSportif(int id) {
        Optional<CentreSportif> centreSportif = centreSportifRepository.findById(id);
        if (centreSportif.isPresent()) {
            centreSportifRepository.delete(centreSportif.get());
            return true;
        }
        return false;
    }
}
