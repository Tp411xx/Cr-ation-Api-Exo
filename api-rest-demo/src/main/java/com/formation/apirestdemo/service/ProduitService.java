package com.formation.apirestdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;
import com.formation.apirestdemo.model.Produit;
import com.formation.apirestdemo.repository.ProduitRepository;

/**
 * Service contenant la logique métier pour les produits.
 * Le service est le chef d'orchestre : il coordonne les appels
 * au repository et aux notifications.
 */
@Service
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final NotificationService notificationService;

    /**
     * Injection de dépendances par constructeur.
     * Spring fournit le repository et l'implémentation de NotificationService.
     */
    public ProduitService(ProduitRepository produitRepository,@Qualifier("consoleNotificationService") NotificationService notificationService) {
    this.produitRepository = produitRepository;
    this.notificationService = notificationService;
}

    /**
     * Retourne tous les produits.
     */
    public List<Produit> recupererTous() {
        return produitRepository.findAll();
    }

    /**
     * Retourne un produit par son ID.
     * @return Optional vide si l'ID n'existe pas.
     */
    public Optional<Produit> recupererParId(Long id) {
        return produitRepository.findById(id);
    }

    /**
     * Crée un nouveau produit et envoie une notification.
     * @param produit Le produit à créer (sans ID).
     * @return Le produit créé (avec ID généré).
     */
    public Produit creer(Produit produit) {
        // On s'assure que l'ID est null pour forcer une création (pas une mise à jour)
        produit.setId(null);
        Produit produitCree = produitRepository.save(produit);

        String nom = produitCree.getNom();
        notificationService.notifier("stock", "Nouveau produit : " + nom);

        return produitCree;
    }

    /**
     * Met à jour un produit existant.
     * @param id L'ID du produit à modifier.
     * @param produitModifie Les nouvelles valeurs.
     * @return Optional contenant le produit mis à jour, ou vide si l'ID n'existe pas.
     */
    public Optional<Produit> mettreAJour(Long id, Produit produitModifie) {
        return produitRepository.findById(id)
                .map(produitExistant -> {
                    produitExistant.setNom(produitModifie.getNom());
                    produitExistant.setDescription(produitModifie.getDescription());
                    produitExistant.setPrix(produitModifie.getPrix());
                    produitExistant.setStock(produitModifie.getStock());
                    return produitRepository.save(produitExistant);
                });
    }

    /**
     * Supprime un produit par son ID.
     * @return true si le produit existait et a été supprimé, false sinon.
     */
    public boolean supprimer(Long id) {
        if (produitRepository.existsById(id)) {
            produitRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Recherche des produits par nom (recherche partielle, insensible à la casse).
     */
    public List<Produit> rechercherParNom(String nom) {
        return produitRepository.findByNomContainingIgnoreCase(nom);
    }
}