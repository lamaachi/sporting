package org.projet.paimentservice.client;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Reservation {

    @Id
    private String id; // L'ID unique de la réservation
    private String status; // Le statut de la réservation (par exemple, 'confirmed', 'pending', etc.)

    // Constructeurs, getters et setters
    public Reservation() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
