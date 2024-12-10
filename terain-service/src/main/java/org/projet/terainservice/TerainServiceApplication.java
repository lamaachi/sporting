package org.projet.terainservice;

import org.projet.terainservice.services.TerrainService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TerainServiceApplication implements CommandLineRunner {
    private final TerrainService terrainService;

    public TerainServiceApplication(TerrainService terrainService) {
        this.terrainService = terrainService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TerainServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        terrainService.runTerrainService();
    }
}
