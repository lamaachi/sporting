package org.projet.centreservice.repositories;

import org.projet.centreservice.entities.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lamaachi
 **/
public interface TerrainRepository extends JpaRepository<Terrain, Integer> {
}
