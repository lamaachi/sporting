package org.projet.reservationservice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {

        return reservationRepository.findAll();
    }

    // Get a single reservation by ID
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
    }

    // Get all reservations for a specific terrain
    public List<Reservation> getAllReservationsByTerrainId(int terrainId) {
        return reservationRepository.findByTerrainId(terrainId);
    }

    public Reservation createReservation(Reservation reservation) {

        return reservationRepository.save(reservation);
    }

    // PATCH: Update partial fields of a reservation
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Optional<Reservation> existingReservationOpt = reservationRepository.findById(id);

        if (existingReservationOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found");
        }

        Reservation existingReservation = existingReservationOpt.get();

        // Update only the fields that are provided (non-null)
        if (updatedReservation.getTerrainId() != 0) {
            existingReservation.setTerrainId(updatedReservation.getTerrainId());
        }
        if (updatedReservation.getPhoneNumber() != null) {
            existingReservation.setPhoneNumber(updatedReservation.getPhoneNumber());
        }
        if (updatedReservation.getReservedBy() != null) {
            existingReservation.setReservedBy(updatedReservation.getReservedBy());
        }
        if (updatedReservation.getReservationDate() != null) {
            existingReservation.setReservationDate(updatedReservation.getReservationDate());
        }

        return reservationRepository.save(existingReservation);
    }
    
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
