package org.projet.centreservice.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lamaachi
 **/
@Data
@Getter @Setter
public class CentreSportifDTO {
    private int id;
    private String nom;
    private String adresse;
    private String horaires;
    private List<TerrainDTO> terrains;
    private List<EquipementDTO> equipements;
}
