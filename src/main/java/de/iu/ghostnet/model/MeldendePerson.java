package de.iu.ghostnet.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Person, die ein Geisternetz meldet. Darf anonym bleiben – dann sind Name und
 * Telefonnummer leer. Für eine Verschollen-Meldung wird allerdings ein Name
 * verlangt (siehe Prüfung in der jeweiligen Bean).
 */
@Entity
@DiscriminatorValue("MELDEND")
public class MeldendePerson extends Person {

    public boolean istAnonym() {
        return getName() == null || getName().trim().isEmpty();
    }
}
