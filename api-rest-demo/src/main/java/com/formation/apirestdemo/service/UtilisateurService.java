package com.formation.apirestdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.formation.apirestdemo.model.Utilisateur;
import com.formation.apirestdemo.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    /**
     * Injection de dépendance par constructeur.
     * Spring injecte automatiquement le repository.
     */
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Retourne tous les utilisateurs.
     */
    public List<Utilisateur> recupererTous() {
        return utilisateurRepository.findAll();
    }

    /**
     * Retourne un utilisateur par son ID.
     * @return Optional vide si l'ID n'existe pas.
     */
    public Optional<Utilisateur> recupererParId(Long id) {
        return utilisateurRepository.findById(id);
    }

    /**
     * Crée un nouvel utilisateur.
     * @param utilisateur L'utilisateur à créer (sans ID).
     * @return L'utilisateur créé (avec ID généré).
     */
    public Utilisateur creer(Utilisateur utilisateur) {
        // On s'assure que l'ID est null pour forcer une création (pas une mise à jour)
        utilisateur.setId(null);
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Met à jour un utilisateur existant.
     * @param id L'ID de l'utilisateur à modifier.
     * @param utilisateurModifie Les nouvelles valeurs.
     * @return Optional contenant l'utilisateur mis à jour, ou vide si l'ID n'existe pas.
     */
    public Optional<Utilisateur> mettreAJour(Long id, Utilisateur utilisateurModifie) {
        return utilisateurRepository.findById(id)
                .map(utilisateurExistant -> {
                    utilisateurExistant.setNom(utilisateurModifie.getNom());
                    utilisateurExistant.setPrenom(utilisateurModifie.getPrenom());
                    utilisateurExistant.setEmail(utilisateurModifie.getEmail());
                    utilisateurExistant.setÂge(utilisateurModifie.getÂge());
                    return utilisateurRepository.save(utilisateurModifie);
                });
    }

    /**
     * Supprime un utilisateur par son ID.
     * @return true si l'utilisateur existait et a été supprimé, false sinon.
     */
    public boolean supprimer(Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Recherche des utilisateurs par email (recherche partielle, insensible à la casse).
     */
    public List<Utilisateur> rechercherParNom(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}
