package org.projet.terainservice.services;

import jakarta.annotation.PostConstruct;
import org.projet.terainservice.RabbitMQ.TerrainEventProducer;
import org.projet.terainservice.dtos.TerrainAssignmentEvent;
import org.projet.terainservice.dtos.TerrainDTO;
import org.projet.terainservice.entities.Terrain;
import org.projet.terainservice.repositories.TerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
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
        return terrains.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Save a new terrain
    public TerrainDTO saveTerrain(TerrainDTO terrainDTO) {
        Terrain terrain = convertToEntity(terrainDTO);
        Terrain savedTerrain = terrainRepository.save(terrain);
        eventSender.sendEvent("ADD_TERRAIN", getAllTerrains());
        eventSender.sendEvent("ALL_EVENTS",getAllTerrains());
        eventSender.sendEvent("ASSIGN_CENTRE_EVENT",new TerrainAssignmentEvent(terrainDTO.getId(),terrainDTO.getCentreId()));
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
            eventSender.sendEvent("UPDATE_TERRAIN_EVENT", terrainDTO);
            eventSender.sendEvent("ALL_TERRAINS_EVENT",getAllTerrains());
            eventSender.sendEvent("ASSIGN_CENTRE_EVENT",new TerrainAssignmentEvent(terrainDTO.getId(),terrainDTO.getCentreId()));

            return convertToDTO(updatedTerrain);
        } else {
            throw new RuntimeException("Terrain not found with id: " + id);
        }
    }

    // Delete a terrain by ID
    public void deleteTerrain(int id) {
        Optional<Terrain> optionalTerrain = terrainRepository.findById(id);
        if (optionalTerrain.isPresent()) {
            terrainRepository.deleteById(id);
        } else {
            throw new RuntimeException("Terrain not found with id: " + id);
        }
    }

    public void runTerrainService() {
        // Run terrain service logic
        eventSender.sendEvent("RUN_TERRAIN", "Service started successfully");
        eventSender.sendEvent("ALL_TERRAINS", getAllTerrains());
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

    public TerrainDTO getTerrainById(int id) {
        // Fetch the terrain from the repository using the ID
        Terrain terrain = terrainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Terrain not found with ID: " + id));

        // Map the entity to a DTO and return it
        return convertToDTO(terrain);
    }

}
