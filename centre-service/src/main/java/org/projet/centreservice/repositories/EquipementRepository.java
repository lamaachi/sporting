package org.projet.centreservice.repositories;

import org.projet.centreservice.entities.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lamaachi
 **/
public interface EquipementRepository extends JpaRepository<Equipement, Integer> {
}
