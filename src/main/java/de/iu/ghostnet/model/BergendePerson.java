package de.iu.ghostnet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Person, die Geisternetze bergen will. Eine bergende Person kann mehrere Netze
 * gleichzeitig bergen, daher die Liste. Die Gegenseite (das Netz) hält die
 * Fremdschlüsselspalte, deshalb hier nur mappedBy.
 */
@Entity
@DiscriminatorValue("BERGEND")
public class BergendePerson extends Person {

    @OneToMany(mappedBy = "berger")
    private List<Geisternetz> netze = new ArrayList<>();

    public List<Geisternetz> getNetze() {
        return netze;
    }
}
