package org.projet.centreservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author lamaachi
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CentreSportif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String adresse;
    private String horaires;

    @OneToMany(mappedBy = "centreSportif", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Terrain> terrains;

    @OneToMany(mappedBy = "centreSportif", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipement> equipements;

}
