package org.projet.registryservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public endpoint, accessible by anyone!";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "User endpoint, accessible by USERS!";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin endpoint, accessible by ADMINS!";
    }
}
