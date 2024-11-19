package org.projet.centreservice.entities;

import jakarta.persistence.*;

/**
 * @author lamaachi
 **/
@Entity
public class Terrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private float prix;
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "centre_sportif_id")
    private CentreSportif centreSportif;
}
