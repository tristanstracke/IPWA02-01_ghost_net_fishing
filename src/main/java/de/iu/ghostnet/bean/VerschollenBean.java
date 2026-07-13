package de.iu.ghostnet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import de.iu.ghostnet.dao.GeisternetzDao;
import de.iu.ghostnet.model.Geisternetz;
import de.iu.ghostnet.model.Status;

/**
 * Melden eines Netzes als verschollen (User Story 7). Anders als beim Erfassen
 * darf das nicht anonym geschehen – ohne Namen wird die Meldung abgewiesen.
 * Die Pflichtangabe ist zusätzlich in der View über required gesichert; die
 * Prüfung hier fängt Umgehungen ab und macht die Regel im Code sichtbar.
 */
@Named
@ViewScoped
public class VerschollenBean implements Serializable {

    private List<Geisternetz> netze;
    private Long ausgewaehltesNetzId;
    private String melderName;

    @PostConstruct
    public void laden() {
        netze = new GeisternetzDao().findeZuBergen();
    }

    public String alsVerschollenMelden() {
        if (melderName == null || melderName.trim().isEmpty()) {
            fehler("Eine Verschollen-Meldung ist nicht anonym möglich. Bitte Namen angeben.");
            return null;
        }

        Geisternetz netz = new GeisternetzDao().finde(ausgewaehltesNetzId);
        if (netz == null) {
            fehler("Bitte ein Netz auswählen.");
            return null;
        }

        netz.setStatus(Status.VERSCHOLLEN);
        new GeisternetzDao().aktualisieren(netz);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Netz als verschollen gemeldet."));
        ausgewaehltesNetzId = null;
        melderName = null;
        laden();
        return null;
    }

    private void fehler(String text) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, text, null));
    }

    public List<Geisternetz> getNetze() {
        return netze;
    }

    public Long getAusgewaehltesNetzId() {
        return ausgewaehltesNetzId;
    }

    public void setAusgewaehltesNetzId(Long ausgewaehltesNetzId) {
        this.ausgewaehltesNetzId = ausgewaehltesNetzId;
    }

    public String getMelderName() {
        return melderName;
    }

    public void setMelderName(String melderName) {
        this.melderName = melderName;
    }
}
