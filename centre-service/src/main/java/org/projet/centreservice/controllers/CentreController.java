package org.projet.centreservice.controllers;

import org.projet.centreservice.dtos.CentreSportifDTO;
import org.projet.centreservice.entities.CentreSportif;
import org.projet.centreservice.services.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing CentreSportif entities.
 * Provides endpoints for CRUD operations.
 *
 * @author lamaachi
 */
@RestController
@RequestMapping("/api/centres")
public class CentreController {

    @Autowired
    private CentreService centreSportifService;

    // Create a new CentreSportif
    @PostMapping
    public ResponseEntity<CentreSportif> createCentreSportif(@RequestBody CentreSportifDTO centreSportifDTO) {
        CentreSportif createdCentre = centreSportifService.createCentreSportif(centreSportifDTO);
        return new ResponseEntity<>(createdCentre, HttpStatus.CREATED);
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
            return new ResponseEntity<>(centreSportif, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update CentreSportif by ID
    @PutMapping("/{id}")
    public ResponseEntity<CentreSportif> updateCentreSportif(@PathVariable int id, @RequestBody CentreSportifDTO centreSportifDTO) {
        CentreSportif updatedCentre = centreSportifService.updateCentreSportif(id, centreSportifDTO);
        if (updatedCentre != null) {
            return new ResponseEntity<>(updatedCentre, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete CentreSportif by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentreSportif(@PathVariable int id) {
        boolean isDeleted = centreSportifService.deleteCentreSportif(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
