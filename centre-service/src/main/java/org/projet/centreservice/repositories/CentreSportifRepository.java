package org.projet.centreservice.repositories;

import org.projet.centreservice.entities.CentreSportif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lamaachi
 **/
public interface CentreSportifRepository extends JpaRepository<CentreSportif, Integer> {

}
