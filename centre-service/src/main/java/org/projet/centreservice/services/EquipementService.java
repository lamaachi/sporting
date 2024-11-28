//package org.projet.centreservice.services;
//
//import org.projet.centreservice.dtos.EquipementDTO;
//import org.projet.centreservice.entities.Equipement;
//import org.projet.centreservice.repositories.CentreSportifRepository;
//import org.projet.centreservice.repositories.EquipementRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class EquipementService {
//    private final EquipementRepository equipementRepository;
//    private final CentreSportifRepository centreSportifRepository;
//
//    public EquipementService(EquipementRepository equipementRepository, CentreSportifRepository centreSportifRepository) {
//        this.equipementRepository = equipementRepository;
//        this.centreSportifRepository = centreSportifRepository;
//    }
//
//    // Create: Save new equipement
//    public EquipementDTO saveEquipement(EquipementDTO equipementDTO) {
//        Equipement equipement = new Equipement();
//        equipement.setNom(equipementDTO.getNom());
//        equipement.setDescription(equipementDTO.getDescription());
//        equipement.setTerrain(equipementDTO.getTerrain());
//        equipement = equipementRepository.save(equipement);
//        equipementDTO.setId(equipement.getId());
//        return equipementDTO;
//    }
//
//    // Read: Get all equipements by centre ID
//    public List<EquipementDTO> getEquipementsByCentre(int centreId) {
//        return equipementRepository.findByCentreSportifId(centreId).stream()
//                .map(equipement -> new EquipementDTO(equipement.getId(), equipement.getNom(), equipement.getDescription(), equipement.getTerrain()))
//                .collect(Collectors.toList());
//    }
//
//    // Read: Get an equipement by its ID
//    public EquipementDTO getEquipementById(int id) {
//        Optional<Equipement> equipement = equipementRepository.findById(id);
//        return equipement.map(e -> new EquipementDTO(e.getId(), e.getNom(), e.getDescription(), null)).orElse(null);
//    }
//
//    // Update: Update an existing equipement
//    public EquipementDTO updateEquipement(EquipementDTO equipementDTO) {
//        Optional<Equipement> optionalEquipement = equipementRepository.findById(equipementDTO.getId());
//        if (optionalEquipement.isPresent()) {
//            Equipement equipement = optionalEquipement.get();
//            equipement.setNom(equipementDTO.getNom());
//            equipement.setDescription(equipementDTO.getDescription());
//            equipement.setTerrain(equipementDTO.getTerrain());
//
//            equipement = equipementRepository.save(equipement);
//            return new EquipementDTO(equipement.getId(), equipement.getNom(), equipement.getDescription(), equipement.getTerrain());
//        } else {
//            return null; // Equipement not found
//        }
//    }
//
//    // Delete: Delete an equipement by its ID
//    public boolean deleteEquipement(int id) {
//        Optional<Equipement> equipement = equipementRepository.findById(id);
//        if (equipement.isPresent()) {
//            equipementRepository.delete(equipement.get());
//            return true;
//        }
//        return false; // Equipement not found
//    }
//}
