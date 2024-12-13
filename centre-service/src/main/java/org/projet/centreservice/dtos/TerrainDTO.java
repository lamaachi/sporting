package org.projet.centreservice.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author lamaachi
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerrainDTO {
    private Long id;
    private String nom;
    private String type;
    private Double prix;
    private Boolean disponible;
    @JsonProperty("centreId")
    private Long centreId;
}