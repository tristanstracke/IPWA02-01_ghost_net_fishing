package de.iu.ghostnet.dao;

import java.util.List;

import javax.persistence.EntityManager;

import de.iu.ghostnet.model.BergendePerson;
import de.iu.ghostnet.model.Person;

/**
 * Datenzugriff für Personen. Meldende und bergende Personen liegen wegen der
 * Single-Table-Vererbung in derselben Tabelle, lassen sich über JPA aber gezielt
 * abfragen.
 */
public class PersonDao {

    public Person speichern(Person person) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

    /** Alle bergenden Personen – z. B. für die Auswahl beim Eintragen einer Bergung. */
    public List<BergendePerson> findeBergendePersonen() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("select b from BergendePerson b order by b.name", BergendePerson.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
