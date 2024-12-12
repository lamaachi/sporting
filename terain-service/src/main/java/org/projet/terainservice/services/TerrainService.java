package org.projet.terainservice.services;

import org.projet.terainservice.RabbitMQ.TerrainEventProducer;
import org.projet.terainservice.dtos.TerrainAssignmentEvent;
import org.projet.terainservice.dtos.TerrainDTO;
import org.projet.terainservice.entities.Terrain;
import org.projet.terainservice.repositories.TerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TerrainService {
    private final TerrainEventProducer eventSender;
    private final TerrainRepository terrainRepository;

    @Autowired
    public TerrainService(TerrainEventProducer eventSender, TerrainRepository terrainRepository) {
        this.eventSender = eventSender;
        this.terrainRepository = terrainRepository;
    }

    // Get all terrains
    public List<TerrainDTO> getAllTerrains() {
        List<Terrain> terrains = terrainRepository.findAll();
        List<TerrainDTO> terrainDTOList = terrains.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        // Send an event for retrieving all terrains
        eventSender.publishEventList("terrain.all", terrainDTOList);
        return terrainDTOList;
    }

    // Save a new terrain
    public TerrainDTO saveTerrain(TerrainDTO terrainDTO) {
        Terrain terrain = convertToEntity(terrainDTO);
        Terrain savedTerrain = terrainRepository.save(terrain);

        // Send events for terrain addition
        eventSender.publishEventOneTerrain("terrain.add", convertToDTO(savedTerrain));
        eventSender.publishEventList("terrain.all", getAllTerrains());
        return convertToDTO(savedTerrain);
    }

    // Update an existing terrain by ID
    public TerrainDTO updateTerrain(int id, TerrainDTO terrainDTO) {
        Optional<Terrain> optionalTerrain = terrainRepository.findById(id);
        if (optionalTerrain.isPresent()) {
            Terrain terrain = optionalTerrain.get();
            terrain.setNom(terrainDTO.getNom());
            terrain.setType(terrainDTO.getType());
            terrain.setPrix(terrainDTO.getPrix());
            terrain.setDisponible(terrainDTO.isDisponible());
            terrain.setCentreId(terrainDTO.getCentreId());
            Terrain updatedTerrain = terrainRepository.save(terrain);

            // Send events for terrain assignment and all terrains update
            eventSender.publishEventAssignment("terrain.assign", new TerrainAssignmentEvent(id, terrainDTO.getCentreId()));
            eventSender.publishEventList("terrain.all", getAllTerrains());
            return convertToDTO(updatedTerrain);
        } else {
            throw new RuntimeException("Terrain not found with ID: " + id);
        }
    }

    // Delete a terrain by ID
    public void deleteTerrain(int id) {
        Optional<Terrain> optionalTerrain = terrainRepository.findById(id);
        if (optionalTerrain.isPresent()) {
            terrainRepository.deleteById(id);

            // Update all terrains after deletion
            eventSender.publishEventList("terrain.all", getAllTerrains());
        } else {
            throw new RuntimeException("Terrain not found with ID: " + id);
        }
    }

    // Run terrain service logic
    public void runTerrainService() {
        eventSender.publishEventList("terrain.all", getAllTerrains());
    }

    // Get terrain by ID
    public TerrainDTO getTerrainById(int id) {
        Terrain terrain = terrainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Terrain not found with ID: " + id));
        return convertToDTO(terrain);
    }

    // Convert Terrain entity to DTO
    private TerrainDTO convertToDTO(Terrain terrain) {
        return new TerrainDTO(
                terrain.getId(),
                terrain.getNom(),
                terrain.getType(),
                terrain.getPrix(),
                terrain.isDisponible(),
                terrain.getCentreId()
        );
    }

    // Convert DTO to Terrain entity
    private Terrain convertToEntity(TerrainDTO terrainDTO) {
        return new Terrain(
                terrainDTO.getId(),
                terrainDTO.getNom(),
                terrainDTO.getType(),
                terrainDTO.getPrix(),
                terrainDTO.isDisponible(),
                terrainDTO.getCentreId()
        );
    }
}
