package com.formation.apirestdemo.service;

import org.springframework.stereotype.Service;

/**
 * Implémentation simulée : n'envoie pas de vrai e-mail,
 * elle affiche le message tel qu'il serait envoyé.
 * Un vrai envoi demanderait la dépendance spring-boot-starter-mail.
 */
@Service
public class EmailNotificationService implements NotificationService {

    @Override
    public void notifier(String categorie, String message) {
        System.out.println("E-mail envoyé à l'équipe " + categorie + " : " + message);
    }
}