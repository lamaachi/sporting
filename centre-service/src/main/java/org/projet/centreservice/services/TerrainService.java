//package org.projet.centreservice.services;
//
//import org.projet.centreservice.dtos.TerrainDTO;
//import org.projet.centreservice.entities.Terrain;
//import org.projet.centreservice.entities.CentreSportif;
//import org.projet.centreservice.repositories.CentreSportifRepository;
//import org.projet.centreservice.repositories.TerrainRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class TerrainService {
//
//    private final TerrainRepository terrainRepository;
//    private final CentreSportifRepository centreSportifRepository;
//
//    public TerrainService(TerrainRepository terrainRepository, CentreSportifRepository centreSportifRepository) {
//        this.terrainRepository = terrainRepository;
//        this.centreSportifRepository = centreSportifRepository;
//    }
//
//    // Save new terrain
//    public TerrainDTO saveTerrain(TerrainDTO terrainDTO) {
//
//        // Create Terrain entity and set its properties
//        Terrain terrain = new Terrain();
//        terrain.setNom(terrainDTO.getNom());
//        terrain.setType(terrainDTO.getType());
//        terrain.setPrix(terrainDTO.getPrix());
//        terrain.setDisponible(terrainDTO.isDisponible());
//
//        // Save Terrain to the repository
//        terrain = terrainRepository.save(terrain);
//
//        // Update the DTO with the generated ID
//        terrainDTO.setId(terrain.getId());
//        return terrainDTO;
//    }
//
//    // Get all terrains
//    public List<TerrainDTO> getAllTerrains() {
//        return terrainRepository.findAll().stream()
//                .map(terrain -> new TerrainDTO(
//                        terrain.getId(),
//                        terrain.getNom(),
//                        terrain.getType(),
//                        terrain.getPrix(),
//                        terrain.isDisponible()
//                ))
//                .collect(Collectors.toList());
//    }
//
//    // Get terrains by centreId
//    public List<TerrainDTO> getTerrainsByCentre(int centreId) {
//        return terrainRepository.findByCentreSportifId(centreId).stream()
//                .map(terrain -> new TerrainDTO(
//                        terrain.getId(),
//                        terrain.getNom(),
//                        terrain.getType(),
//                        terrain.getPrix(),
//                        terrain.isDisponible()
//                ))
//                .collect(Collectors.toList());
//    }
//
//    // Update terrain by ID
//    public TerrainDTO updateTerrain(int id, TerrainDTO terrainDTO) {
//        Terrain existingTerrain = terrainRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Terrain not found"));
//
//        // Update the Terrain properties
//        existingTerrain.setNom(terrainDTO.getNom());
//        existingTerrain.setType(terrainDTO.getType());
//        existingTerrain.setPrix(terrainDTO.getPrix());
//        existingTerrain.setDisponible(terrainDTO.isDisponible());
//
//        // Save the updated Terrain
//        terrainRepository.save(existingTerrain);
//
//        // Set the updated ID in the DTO
//        terrainDTO.setId(existingTerrain.getId());
//        return terrainDTO;
//    }
//
//    // Delete terrain by ID
//    public void deleteTerrain(int id) {
//        terrainRepository.deleteById(id);
//    }
//}
