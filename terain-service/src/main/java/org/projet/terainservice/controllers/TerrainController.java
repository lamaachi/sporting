package org.projet.terainservice.controllers;

//import org.projet.terainservice.dtos.TerrainAssignmentEvent;
import org.projet.terainservice.dtos.TerrainDTO;
import org.projet.terainservice.services.TerrainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/terrains")
public class TerrainController {

    private final TerrainService terrainService;

    public TerrainController(TerrainService terrainService) {
        this.terrainService = terrainService;
    }


    // Get all terrains
    @GetMapping
    public ResponseEntity<List<TerrainDTO>> getAll() {
        try {
            List<TerrainDTO> terrains = terrainService.getAllTerrains();
            if (terrains.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(terrains);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Create new terrain
    @PostMapping
    public ResponseEntity<TerrainDTO> addTerrain(@RequestBody TerrainDTO terrainDTO) {
        try {
            TerrainDTO createdTerrain = terrainService.saveTerrain(terrainDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTerrain);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get a specific terrain by ID
    @GetMapping("/{id}")
    public ResponseEntity<TerrainDTO> getTerrainById(@PathVariable int id) {
        try {
            TerrainDTO terrain = terrainService.getTerrainById(id);
            return ResponseEntity.ok(terrain);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update a terrain
    @PutMapping("/{id}")
    public ResponseEntity<TerrainDTO> updateTerrain(@PathVariable int id, @RequestBody TerrainDTO terrainDTO) {
        try {
            TerrainDTO updatedTerrain = terrainService.updateTerrain(id, terrainDTO);
            return ResponseEntity.ok(updatedTerrain);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Delete a terrain
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTerrain(@PathVariable int id) {
        try {
            terrainService.deleteTerrain(id);
            return ResponseEntity.ok("Terrain deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting terrain: " + e.getMessage());
        }
    }

    // Get terrains by centreId
    @GetMapping("/centre/{centreId}")
    public ResponseEntity<List<TerrainDTO>> getTerrainsByCentre(@PathVariable int centreId) {
        try {
            List<TerrainDTO> terrains = terrainService.getAllTerrains()
                    .stream()
                    .filter(terrain -> terrain.getCentreId() == centreId)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(terrains);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
