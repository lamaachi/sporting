package org.projet.terainservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * @author lamaachi
 **/
@Entity
public class Terrain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nom;
    private String type;
    private float prix;
    private boolean disponible; // yes or no for reservation
    private int centreId;

    public Terrain() {}

    public Terrain(int id, String nom, String type, float prix, boolean disponible, int centreId) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.disponible = disponible;
        this.centreId = centreId;
    }

    public int getCentreId() {
        return centreId;
    }

    public void setCentreId(int centreId) {
        this.centreId = centreId;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
