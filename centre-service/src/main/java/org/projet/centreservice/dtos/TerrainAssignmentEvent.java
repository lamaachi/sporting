package org.projet.centreservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lamaachi
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TerrainAssignmentEvent {
    private Long terrainId;
    private int centreId;
    private String assignmentStatus; // Example: ASSIGNED, FAILED
}
