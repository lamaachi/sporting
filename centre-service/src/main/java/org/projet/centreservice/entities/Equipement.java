//package org.projet.centreservice.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
///**
// * @author lamaachi
// **/
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Getter
//@Setter
//public class Equipement {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String nom;
//    private String description;
//
//    // Many Equipments can belong to one Terrain
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "terrain_id")
//    private Terrain terrain;
//}
