package com.formation.apirestdemo.service;

/**
 * Contrat commun pour l'envoi de notifications.
 * Chaque implémentation décide comment le message est envoyé.
 */
public interface NotificationService {

    /**
     * Envoie une notification.
     * @param categorie Le type de notification (ex. "stock").
     * @param message Le contenu du message.
     */
    void notifier(String categorie, String message);
}