package org.projet.centreservice.dtos;

import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * @author lamaachi
 **/
@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CentreSportifDTO {
    private int id;
    private String nom;
    private String adresse;
    private String horaires;
}
