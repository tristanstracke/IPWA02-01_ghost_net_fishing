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
 * Übersicht der noch zu bergenden Netze (User Story 3) und das Melden einer
 * erfolgreichen Bergung (User Story 4). Nach jeder Aktion wird die Liste neu
 * geladen, damit geborgene Netze verschwinden.
 */
@Named
@ViewScoped
public class OffeneNetzeBean implements Serializable {

    private List<Geisternetz> netze;

    @PostConstruct
    public void laden() {
        netze = new GeisternetzDao().findeZuBergen();
    }

    public String alsGeborgenMelden(Geisternetz netz) {
        netz.setStatus(Status.GEBORGEN);
        new GeisternetzDao().aktualisieren(netz);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Netz als geborgen gemeldet."));
        laden();
        return null;
    }

    public List<Geisternetz> getNetze() {
        return netze;
    }
}
