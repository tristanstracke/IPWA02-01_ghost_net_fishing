package de.iu.ghostnet.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Ein im Meer treibendes, herrenloses Fischernetz.
 *
 * Standort wird über Breiten- und Längengrad abgebildet. Die Größe ist nur eine
 * Schätzung und wird als Freitext gehalten (z. B. "ca. 5 x 3 m"), weil im
 * Fachkonzept keine feste Einheit vorgegeben ist.
 *
 * Ein Netz kann höchstens einer bergenden Person zugeordnet sein – das ergibt
 * sich direkt aus der ManyToOne-Beziehung. Solange niemand bergen will, ist
 * berger schlicht null.
 */
@Entity
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double breitengrad;

    private double laengengrad;

    private String geschaetzteGroesse;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Wer hat das Netz gemeldet? Bei anonymer Meldung null.
    @ManyToOne
    private MeldendePerson melder;

    // Wer kümmert sich um die Bergung? Vor der Zuordnung null.
    @ManyToOne
    private BergendePerson berger;

    public Long getId() {
        return id;
    }

    public double getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(double breitengrad) {
        this.breitengrad = breitengrad;
    }

    public double getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(double laengengrad) {
        this.laengengrad = laengengrad;
    }

    public String getGeschaetzteGroesse() {
        return geschaetzteGroesse;
    }

    public void setGeschaetzteGroesse(String geschaetzteGroesse) {
        this.geschaetzteGroesse = geschaetzteGroesse;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MeldendePerson getMelder() {
        return melder;
    }

    public void setMelder(MeldendePerson melder) {
        this.melder = melder;
    }

    public BergendePerson getBerger() {
        return berger;
    }

    public void setBerger(BergendePerson berger) {
        this.berger = berger;
    }
}
