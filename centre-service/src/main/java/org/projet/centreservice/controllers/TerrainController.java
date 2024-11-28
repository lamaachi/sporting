//package org.projet.centreservice.controllers;
//
//import org.projet.centreservice.dtos.TerrainDTO;
//import org.projet.centreservice.services.TerrainService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/terrains")
//public class TerrainController {
//    private final TerrainService terrainService;
//
//    public TerrainController(TerrainService terrainService) {
//        this.terrainService = terrainService;
//    }
//
//    // Get all terrains
//    @GetMapping
//    public ResponseEntity<List<TerrainDTO>> getAll() {
//        try {
//            List<TerrainDTO> terrains = terrainService.getAllTerrains();
//            if (terrains.isEmpty()) {
//                return ResponseEntity.noContent().build(); // Return 204 if no terrains found
//            }
//            return ResponseEntity.ok(terrains); // Return 200 with the list of terrains
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null); // Return 500 with no body in case of an error
//        }
//    }
//
//    // Create new terrain
//    @PostMapping
//    public ResponseEntity<TerrainDTO> addTerrain(@RequestBody TerrainDTO terrainDTO) {
//        TerrainDTO createdTerrain = terrainService.saveTerrain(terrainDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdTerrain); // 201 Created
//    }
//
//    // Get terrains by centreId
//    @GetMapping("/centre/{centreId}")
//    public ResponseEntity<List<TerrainDTO>> getTerrains(@PathVariable int centreId) {
//        return ResponseEntity.ok(terrainService.getTerrainsByCentre(centreId));
//    }
//
//    // Update terrain by ID
//    @PutMapping("/{id}")
//    public ResponseEntity<TerrainDTO> updateTerrain(@PathVariable int id, @RequestBody TerrainDTO terrainDTO) {
//        TerrainDTO updatedTerrain = terrainService.updateTerrain(id, terrainDTO);
//        return ResponseEntity.ok(updatedTerrain);
//    }
//
//    // Delete terrain by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTerrain(@PathVariable int id) {
//        terrainService.deleteTerrain(id);
//        return ResponseEntity.noContent().build(); // 204 No Content
//    }
//}
