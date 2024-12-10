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

    private final CentreEventProducer centreEventProducer;
    private final CentreSportifRepository centreSportifRepository;
    public CentreService(CentreEventProducer eventProducer, CentreSportifRepository centreSportifRepository) {
        this.centreEventProducer = eventProducer;
        this.centreSportifRepository = centreSportifRepository;
    }

    public void runCentreService() {
        // Send the list of centres in the event
        centreEventProducer.sendEvent("RUN_CENTRE", "Service started successfully");
        centreEventProducer.sendEvent("CENTRE_LIST", getAllCentreSportifs());
    }

    // This method assigns a terrain to a centre
    public void assignTerrainToCentre() {
        centreEventProducer.sendEvent("ASSIGNED_TERRAIN_EVENT_DONE","Terrain assigned successfully");
    }

    // Create CentreSportif
    public CentreSportif createCentreSportif(CentreSportifDTO centreSportifDTO) {
        CentreSportif centreSportif = new CentreSportif();
        centreSportif.setNom(centreSportifDTO.getNom());
        centreSportif.setAdresse(centreSportifDTO.getAdresse());
        centreSportif.setHoraires(centreSportifDTO.getHoraires());

        CentreSportif centreSportif1 = centreSportifRepository.save(centreSportif);
        // Set terrains and equipements here if needed
        centreEventProducer.sendEvent("NEW_TERRAIN_EVENT", centreSportif);
        centreEventProducer.sendEvent("ALL_TERRAINS_EVENT",getAllCentreSportifs());

        return centreSportif1;
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

            CentreSportif updatedCentre = centreSportifRepository.save(centreSportif);
            centreEventProducer.sendEvent("UPDATE_TERRAIN_EVENT", centreSportifDTO);
            centreEventProducer.sendEvent("ALL_TERRAINS_EVENT",getAllCentreSportifs());
            // Update terrains and equipements if needed
            return updatedCentre;
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
