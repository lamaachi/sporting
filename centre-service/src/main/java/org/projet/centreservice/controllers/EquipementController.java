//package org.projet.centreservice.controllers;
//
//import org.projet.centreservice.dtos.EquipementDTO;
//import org.projet.centreservice.services.EquipementService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/equipements")
//public class EquipementController {
//    private final EquipementService equipementService;
//
//    public EquipementController(EquipementService equipementService) {
//        this.equipementService = equipementService;
//    }
//
//    // Create: Add a new equipement
//    @PostMapping
//    public ResponseEntity<EquipementDTO> addEquipement(@RequestBody EquipementDTO equipementDTO) {
//        return ResponseEntity.ok(equipementService.saveEquipement(equipementDTO));
//    }
//
//    // Read: Get equipements by centre
//    @GetMapping("/centre/{centreId}")
//    public ResponseEntity<List<EquipementDTO>> getEquipements(@PathVariable int centreId) {
//        return ResponseEntity.ok(equipementService.getEquipementsByCentre(centreId));
//    }
//
//    // Read: Get an equipement by its ID
//    @GetMapping("/{id}")
//    public ResponseEntity<EquipementDTO> getEquipementById(@PathVariable int id) {
//        EquipementDTO equipementDTO = equipementService.getEquipementById(id);
//        if (equipementDTO != null) {
//            return ResponseEntity.ok(equipementDTO);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Update: Update an existing equipement
//    @PutMapping("/{id}")
//    public ResponseEntity<EquipementDTO> updateEquipement(@PathVariable int id, @RequestBody EquipementDTO equipementDTO) {
//        equipementDTO.setId(id);  // Ensure the DTO has the correct ID
//        EquipementDTO updatedEquipement = equipementService.updateEquipement(equipementDTO);
//        if (updatedEquipement != null) {
//            return ResponseEntity.ok(updatedEquipement);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Delete: Delete an equipement by its ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEquipement(@PathVariable int id) {
//        boolean deleted = equipementService.deleteEquipement(id);
//        if (deleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
