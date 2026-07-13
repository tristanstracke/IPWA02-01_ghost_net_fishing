package de.iu.ghostnet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hält die EntityManagerFactory für die gesamte Anwendung. Die Factory ist teuer
 * im Aufbau und wird deshalb nur einmal erzeugt; einzelne EntityManager sind
 * dagegen billig und werden pro Datenbankoperation in den DAOs geholt und wieder
 * geschlossen.
 */
public final class JPAUtil {

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("ghostnetPU");

    private JPAUtil() {
        // Utility-Klasse, keine Instanzen
    }

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }
}
