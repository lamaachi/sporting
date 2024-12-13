package org.projet.terainservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.projet.terainservice.RabbitMQ.TerrainEventProducer;
import org.projet.terainservice.dtos.TerrainDTO;
import org.projet.terainservice.entities.Terrain;
import org.projet.terainservice.repositories.TerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TerrainService {
    private final TerrainEventProducer eventSender;
    private final TerrainRepository terrainRepository;
    private final ObjectMapper objectMapper;
    @Autowired
    public TerrainService(TerrainEventProducer eventSender, TerrainRepository terrainRepository, ObjectMapper objectMapper) {
        this.eventSender = eventSender;
        this.terrainRepository = terrainRepository;
        this.objectMapper = objectMapper;
    }

    // Get all terrains
    public List<TerrainDTO> getAllTerrains() {
        // Retrieve all terrains from the repository
        List<Terrain> terrains = terrainRepository.findAll();

        // Convert Terrain entities to DTOs
        List<TerrainDTO> terrainDTOList = terrains.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        // Convert the list of TerrainDTOs to a JSON string
        try {
            // Convert the list of TerrainDTOs to a JSON string
            String terrainJson = objectMapper.writeValueAsString(terrainDTOList);

            // Send the JSON string as an event message
            eventSender.publishEventList("terrain.all", terrainJson);

            System.out.println("Sent terrain list as JSON: " + terrainJson);
        } catch (Exception e) {
            // Handle any serialization errors
            System.err.println("Failed to serialize terrain list to JSON");
            e.printStackTrace();
        }

        // Return the list of TerrainDTOs
        return terrainDTOList;
    }


    // Save a new terrain
    public TerrainDTO saveTerrain(TerrainDTO terrainDTO) {
        try {
            // Convert DTO to Entity
            Terrain terrain = convertToEntity(terrainDTO);
            // Save the terrain
            Terrain savedTerrain = terrainRepository.save(terrain);

            // Create a JSON object with required fields
            Map<String, Object> eventData = new HashMap<>();
            eventData.put("terrainId", terrain.getId());
            eventData.put("centreId", terrain.getCentreId());

            // Convert the map to a JSON string
            String jsonMessageAssign = objectMapper.writeValueAsString(eventData);

            // Convert saved terrain entity to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String terrainJson = objectMapper.writeValueAsString(convertToDTO(savedTerrain)); // Convert DTO to JSON string

            // Publish events with JSON data
            eventSender.publishEventOneTerrain("terrain.add", terrainJson);
            eventSender.publishEventAssignment("terrain.assign", jsonMessageAssign);

            // Return the saved terrain DTO
            return convertToDTO(savedTerrain);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save terrain", e);  // or any custom exception
        }
    }

    // Update an existing terrain by ID
    public TerrainDTO updateTerrain(int id, TerrainDTO terrainDTO) throws JsonProcessingException {
        Optional<Terrain> optionalTerrain = terrainRepository.findById(id);
        if (optionalTerrain.isPresent()) {
            Terrain terrain = optionalTerrain.get();

            // Update the terrain details
            terrain.setNom(terrainDTO.getNom());
            terrain.setType(terrainDTO.getType());
            terrain.setPrix(terrainDTO.getPrix());
            terrain.setDisponible(terrainDTO.isDisponible());
            terrain.setCentreId(terrainDTO.getCentreId());

            // Save the updated terrain
            Terrain updatedTerrain = terrainRepository.save(terrain);

            // Convert the updated TerrainDTO to JSON string
            String terrainJson = null;
            try {
                terrainJson = objectMapper.writeValueAsString(convertToDTO(updatedTerrain)); // Convert DTO to JSON string
            } catch (Exception e) {
                throw new RuntimeException("Error converting TerrainDTO to JSON: " + e.getMessage());
            }

            // Create a JSON object with required fields
            Map<String, Object> eventData = new HashMap<>();
            eventData.put("terrainId", terrain.getId());
            eventData.put("centreId", terrain.getCentreId());

            // Convert the map to a JSON string
            String jsonMessageAssign = objectMapper.writeValueAsString(eventData);
            // Send events for terrain assignment and all terrains update
            eventSender.publishEventOneTerrain("terrain.update", terrainJson);
            eventSender.publishEventAssignment("terrain.assign", jsonMessageAssign);// Send JSON string event

            // Return the updated TerrainDTO
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
//            eventSender.publishEventList("terrain.all", getAllTerrains());
        } else {
            throw new RuntimeException("Terrain not found with ID: " + id);
        }
    }

    // Run terrain service logic
    public void runTerrainService() {
//        eventSender.publishEventList("terrain.all", getAllTerrains());
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
