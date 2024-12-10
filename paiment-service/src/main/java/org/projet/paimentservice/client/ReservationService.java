//package org.projet.paimentservice.client;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ReservationService {
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    // Méthode pour confirmer une réservation
//    public void confirmReservation(String reservationId) {
//        // Trouver la réservation par son ID
//        Reservation reservation = reservationRepository.findById(reservationId)
//                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
//
//        // Changer le statut de la réservation à 'confirmée'
//        reservation.setStatus("confirmed");
//
//        // Sauvegarder la réservation mise à jour dans la base de données
//        reservationRepository.save(reservation);
//
//        // Vous pouvez ajouter des notifications ou d'autres actions ici
//    }
//}
//
