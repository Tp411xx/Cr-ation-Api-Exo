package com.formation.apirestdemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * Entité JPA représentant un produit.
 * Chaque instance correspond à une ligne dans la table "utilisateur".
 */
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    /**
     * Identifiant unique, généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nom de l'utilisateur. Ne peut pas être vide.
     */
    @NotBlank(message = "Le nom de l'utilisateur est obligatoire")
    @Column(nullable = false)
    private String nom;
    /** 
     * Prenom de l'utilisateur. Ne peut pas être vide.
     */
    @NotBlank(message = "Le prenom de l'utilisateur est obligatoire")
    @Column(nullable = false)
    private String prenom;

    /** 
     * Prenom de l'utilisateur. Ne peut pas être vide.
     */
    @NotBlank(message = "L'email est obligatoire")
    @Column(nullable = false, unique=true)
    @Email(message = "L'email est incorrect")
    private String email;


    /**
     * L'âge doit être positif.
     */
    @Positive(message = "L'âge doit être positif")
    @Column(nullable = false)
    private Integer âge;



    // --- Constructeurs ---

    /**
     * Constructeur sans arguments (requis par JPA).
     */
    public Utilisateur() {
    }

    /**
     * Constructeur avec tous les champs (sauf l'id, généré automatiquement).
     */
    public Utilisateur(String nom, String prenom, String email, Integer âge) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.âge = âge;
    }

    // --- Getters et Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getÂge() {
        return âge;
    }

    public void setÂge(Integer âge) {
        this.âge = âge;
    }

    @Override
    public String toString() {
        return "Utilisateur{id=" + id + ", nom='" + nom + "', prenom=" + prenom + "', email=" + email + ", âge=" + âge + "}";
    }
}