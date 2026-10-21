package com.formation.apirestdemo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formation.apirestdemo.model.Utilisateur;
import com.formation.apirestdemo.service.UtilisateurService;

import jakarta.validation.Valid;

/**
 * Contrôleur REST pour les utilisateurs.
 * Chaque méthode correspond à un endpoint de l'API.
 *
 * @RestController = @Controller + @ResponseBody
 * → Toutes les méthodes retournent du JSON (pas des vues HTML).
 */
@RestController
@RequestMapping("/api/v1/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // ============================================================
    // GET /api/v1/utilisateurs — Lister tous les utilisateurs
    // ============================================================
    @GetMapping
    public ResponseEntity<List<Utilisateur>> listerTous() {
        List<Utilisateur> utilisateurs = utilisateurService.recupererTous();
        return ResponseEntity.ok(utilisateurs); // 200 OK
    }

    // ============================================================
    // GET /api/v1/utilisateurs/{id} — Obtenir un utilisateur par son ID
    // ============================================================
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> obtenirParId(@PathVariable Long id) {
        return utilisateurService.recupererParId(id)
                .map(ResponseEntity::ok)                    // 200 OK si trouvé
                .orElse(ResponseEntity.notFound().build()); // 404 si non trouvé
    }

    // ============================================================
    // POST /api/v1/utilisateurs — Créer un nouvel utilisateur
    // ============================================================
    @PostMapping
    public ResponseEntity<Utilisateur> creer(@Valid @RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurCree = utilisateurService.creer(utilisateur);
        return ResponseEntity
                .status(HttpStatus.CREATED) // 201 Created
                .body(utilisateurCree);
    }

    // ============================================================
    // PUT /api/v1/utilisateurs/{id} — Mettre à jour un utilisateur
    // ============================================================
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> mettreAJour(@PathVariable Long id,@Valid @RequestBody Utilisateur utilisateur) {
        return utilisateurService.mettreAJour(id, utilisateur)
                .map(ResponseEntity::ok)                    // 200 OK si mis à jour
                .orElse(ResponseEntity.notFound().build()); // 404 si non trouvé
    }

    // ============================================================
    // DELETE /api/v1/utilisateurs/{id} — Supprimer un utilisateur
    // ============================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        if (utilisateurService.supprimer(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build();      // 404 si non trouvé
    }
}