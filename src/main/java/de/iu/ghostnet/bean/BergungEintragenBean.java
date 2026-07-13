package de.iu.ghostnet.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import de.iu.ghostnet.dao.GeisternetzDao;
import de.iu.ghostnet.dao.PersonDao;
import de.iu.ghostnet.model.BergendePerson;
import de.iu.ghostnet.model.Geisternetz;
import de.iu.ghostnet.model.Status;

/**
 * Eine bergende Person trägt sich für die Bergung eines Netzes ein
 * (User Story 2). Zur Auswahl stehen nur Netze, die noch niemandem zugeordnet
 * sind – ein Netz kann höchstens von einer Person geborgen werden.
 */
@Named
@ViewScoped
public class BergungEintragenBean implements Serializable {

    private List<Geisternetz> freieNetze;
    private Long ausgewaehltesNetzId;

    private String bergerName;
    private String bergerTelefon;

    @PostConstruct
    public void laden() {
        // aus den noch zu bergenden Netzen nur die ohne Berger anbieten
        freieNetze = new GeisternetzDao().findeZuBergen().stream()
                .filter(n -> n.getBerger() == null)
                .collect(Collectors.toList());
    }

    public String eintragen() {
        Geisternetz netz = new GeisternetzDao().finde(ausgewaehltesNetzId);
        if (netz == null) {
            fehler("Bitte ein Netz auswählen.");
            return null;
        }

        BergendePerson berger = new BergendePerson();
        berger.setName(bergerName.trim());
        berger.setTelefonnummer(bergerTelefon.trim());
        new PersonDao().speichern(berger);

        netz.setBerger(berger);
        netz.setStatus(Status.BERGUNG_BEVORSTEHEND);
        new GeisternetzDao().aktualisieren(netz);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Bergung eingetragen. Viel Erfolg!"));
        ausgewaehltesNetzId = null;
        bergerName = null;
        bergerTelefon = null;
        laden();
        return null;
    }

    private void fehler(String text) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, text, null));
    }

    public List<Geisternetz> getFreieNetze() {
        return freieNetze;
    }

    public Long getAusgewaehltesNetzId() {
        return ausgewaehltesNetzId;
    }

    public void setAusgewaehltesNetzId(Long ausgewaehltesNetzId) {
        this.ausgewaehltesNetzId = ausgewaehltesNetzId;
    }

    public String getBergerName() {
        return bergerName;
    }

    public void setBergerName(String bergerName) {
        this.bergerName = bergerName;
    }

    public String getBergerTelefon() {
        return bergerTelefon;
    }

    public void setBergerTelefon(String bergerTelefon) {
        this.bergerTelefon = bergerTelefon;
    }
}
