package de.iu.ghostnet.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Gemeinsame Oberklasse für meldende und bergende Personen. Beide sind
 * natürliche Personen mit Name und Telefonnummer.
 *
 * Es wird die Single-Table-Strategie verwendet: meldende und bergende Personen
 * landen in einer Tabelle und werden über eine Diskriminatorspalte
 * unterschieden. Für die paar Felder hier ist das die einfachste Variante.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rolle")
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String telefonnummer;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
}
