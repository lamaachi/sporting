package org.projet.centreservice.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lamaachi
 **/
@Data
@Getter
@Setter
public class TerrainDTO {
    private int id;
    private String type;
    private float prix;
    private boolean disponible;
}

