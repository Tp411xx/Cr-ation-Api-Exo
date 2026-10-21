package com.formation.apirestdemo.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Implémentation par défaut : affiche la notification dans la console.
 * @Primary indique à Spring quelle implémentation injecter quand il y en a plusieurs.
 */
@Service
@Primary
public class ConsoleNotificationService implements NotificationService {

    @Override
    public void notifier(String categorie, String message) {
        System.out.println("[" + categorie.toUpperCase() + "] " + message);
    }
}