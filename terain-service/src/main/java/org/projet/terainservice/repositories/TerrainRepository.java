package org.projet.terainservice.repositories;

import org.projet.terainservice.entities.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lamaachi
 **/
@Repository
public interface TerrainRepository extends JpaRepository<Terrain, Integer> {
}
