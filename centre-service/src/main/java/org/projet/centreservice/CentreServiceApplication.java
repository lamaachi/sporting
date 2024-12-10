package org.projet.centreservice;

import org.projet.centreservice.services.CentreService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CentreServiceApplication implements CommandLineRunner {

    public CentreServiceApplication(CentreService centreService) {
        this.centreService = centreService;
    }

    private final CentreService centreService;


    public static void main(String[] args) {
        SpringApplication.run(CentreServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        centreService.runCentreService();
    }
}
