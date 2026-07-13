package de.iu.ghostnet.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import de.iu.ghostnet.dao.GeisternetzDao;
import de.iu.ghostnet.dao.PersonDao;
import de.iu.ghostnet.model.Geisternetz;
import de.iu.ghostnet.model.MeldendePerson;
import de.iu.ghostnet.model.Status;

/**
 * Erfassen eines neuen Geisternetzes (User Story 1). Die Meldung darf anonym
 * erfolgen – bleibt das Namensfeld leer, wird gar keine meldende Person
 * angelegt.
 */
@Named
@ViewScoped
public class GeisternetzErfassenBean implements Serializable {

    private double breitengrad;
    private double laengengrad;
    private String geschaetzteGroesse;

    // optionale Angaben zur meldenden Person
    private String melderName;
    private String melderTelefon;

    public String erfassen() {
        Geisternetz netz = new Geisternetz();
        netz.setBreitengrad(breitengrad);
        netz.setLaengengrad(laengengrad);
        netz.setGeschaetzteGroesse(geschaetzteGroesse);
        netz.setStatus(Status.GEMELDET);

        if (melderName != null && !melderName.trim().isEmpty()) {
            MeldendePerson melder = new MeldendePerson();
            melder.setName(melderName.trim());
            melder.setTelefonnummer(melderTelefon);
            new PersonDao().speichern(melder);
            netz.setMelder(melder);
        }

        new GeisternetzDao().speichern(netz);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Das Geisternetz wurde gemeldet. Danke!"));
        zuruecksetzen();
        return null;
    }

    private void zuruecksetzen() {
        breitengrad = 0;
        laengengrad = 0;
        geschaetzteGroesse = null;
        melderName = null;
        melderTelefon = null;
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

    public String getMelderName() {
        return melderName;
    }

    public void setMelderName(String melderName) {
        this.melderName = melderName;
    }

    public String getMelderTelefon() {
        return melderTelefon;
    }

    public void setMelderTelefon(String melderTelefon) {
        this.melderTelefon = melderTelefon;
    }
}
