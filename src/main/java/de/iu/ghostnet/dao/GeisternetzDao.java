package de.iu.ghostnet.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import de.iu.ghostnet.model.Geisternetz;
import de.iu.ghostnet.model.Status;

/**
 * Datenzugriff für Geisternetze (Data Access Object). Kapselt das
 * EntityManager-Handling, sodass die Beans nur noch mit fachlichen Methoden
 * arbeiten.
 */
public class GeisternetzDao {

    public void speichern(Geisternetz netz) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(netz);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /** Speichert Änderungen an einem bereits vorhandenen Netz zurück. */
    public Geisternetz aktualisieren(Geisternetz netz) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Geisternetz gemerged = em.merge(netz);
            em.getTransaction().commit();
            return gemerged;
        } finally {
            em.close();
        }
    }

    public Geisternetz finde(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Geisternetz.class, id);
        } finally {
            em.close();
        }
    }

    public List<Geisternetz> findeAlle() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("select g from Geisternetz g order by g.id", Geisternetz.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Netze, die noch zu bergen sind – also gemeldet oder mit angekündigter
     * Bergung. Geborgene und verschollene Netze tauchen hier bewusst nicht auf.
     */
    public List<Geisternetz> findeZuBergen() {
        List<Status> offen = Arrays.asList(Status.GEMELDET, Status.BERGUNG_BEVORSTEHEND);
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "select g from Geisternetz g where g.status in :offen order by g.id",
                    Geisternetz.class)
                    .setParameter("offen", offen)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
