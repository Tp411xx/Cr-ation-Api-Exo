package com.formation.apirestdemo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur de test : renvoie un message de bienvenue.
 */
@RestController
@RequestMapping("/api/v1")
public class BonjourController {

    @GetMapping("/bonjour")
    public Map<String, String> bonjour() {
    return Map.of("message", "Bonjour le monde !");
}

    @GetMapping("/bonjour/{prenom}")
    public Map<String, String> bonjourPrenom(@PathVariable String prenom) {
    return Map.of("message", "Bonjour " + prenom + " !");
}

    @PostMapping("/bonjour")
    public ResponseEntity<Map<String, String>> bienvenuePrenom(@RequestBody Map<String, String> corps) {
    String prenom = corps.get("prenom");

    if (prenom == null || prenom.isBlank()) {
         return ResponseEntity.badRequest().body(Map.of("erreur", "Le prénom est obligatoire"));
    }

    return ResponseEntity.ok(Map.of("message", "Bienvenue " + prenom + " !"));
}


}