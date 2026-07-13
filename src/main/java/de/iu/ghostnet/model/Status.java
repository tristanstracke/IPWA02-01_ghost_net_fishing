package de.iu.ghostnet.model;

/**
 * Die vier möglichen Zustände eines Geisternetzes laut Fachkonzept.
 */
public enum Status {

    GEMELDET("Gemeldet"),
    BERGUNG_BEVORSTEHEND("Bergung bevorstehend"),
    GEBORGEN("Geborgen"),
    VERSCHOLLEN("Verschollen");

    private final String bezeichnung;

    Status(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    // wird in den XHTML-Seiten für die Anzeige genutzt
    public String getBezeichnung() {
        return bezeichnung;
    }
}
