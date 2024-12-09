package org.projet.centreservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * @author lamaachi
 **/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CentreSportif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String adresse;
    private String horaires;

    @ElementCollection
    private List<Integer> assignedTerrains;  // List of terrain IDs assigned to this centre
}
