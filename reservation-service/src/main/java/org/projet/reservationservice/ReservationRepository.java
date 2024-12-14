package org.projet.reservationservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Find all reservations by terrainId
    List<Reservation> findByTerrainId(int terrainId);
}
