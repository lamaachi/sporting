package org.projet.terainservice.dtos;


import java.io.Serializable;

/**
 * @author lamaachi
 **/
public class TerrainDTO implements Serializable {
    private int id;
    private String nom;
    private String type;
    private float prix;
    private boolean disponible;
    private int centreId;

    public TerrainDTO() {}

    public TerrainDTO(int id, String nom, String type, float prix, boolean disponible, int centreId) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.disponible = disponible;
        this.centreId = centreId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getCentreId() {
        return centreId;
    }

    public void setCentreId(int centreId) {
        this.centreId = centreId;
    }


}

