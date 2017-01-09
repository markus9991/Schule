import de.hamster.debugger.model.Territorium;import de.hamster.debugger.model.Territory;import de.hamster.model.HamsterException;import de.hamster.model.HamsterInitialisierungsException;import de.hamster.model.HamsterNichtInitialisiertException;import de.hamster.model.KachelLeerException;import de.hamster.model.MauerDaException;import de.hamster.model.MaulLeerException;import de.hamster.model.MouthEmptyException;import de.hamster.model.WallInFrontException;import de.hamster.model.TileEmptyException;import de.hamster.debugger.model.Hamster;public class hamsterprogramm implements de.hamster.model.HamsterProgram {public void main() {
    // Aktionsverzeichnis fuellen
    Map<String, HamsterAktion> aktionsVerzeichnis = new Map<String, HamsterAktion>();
    aktionsVerzeichnis.put("vor", new VorAktion());
    aktionsVerzeichnis.put("linksUm", new LinksUmAktion());
    aktionsVerzeichnis.put("nimm", new NimmAktion());
    aktionsVerzeichnis.put("gib", new GibAktion());

    Map<String, Hamster> hamsterVerzeichnis = new Map<String, Hamster>();

    Hamster paul = Hamster.getStandardHamster();

    // Erfassung der Hamster
    boolean ende = false;
    while (!ende) {
        String name = paul
                .liesZeichenkette("Neuer Name (oder ende): ");
        if (name.equals("ende")) {
            ende = true;
        } else if (hamsterVerzeichnis.contains(name)) {
            paul.schreib("Hamstername " + name
                    + " ist bereits vergeben!");
        } else {
            Hamster hamster = new Hamster(paul);
            hamsterVerzeichnis.put(name, hamster);
        }
    }

    // Hamstersteuerung
    ende = false;
    while (!ende) {
        String name = paul
                .liesZeichenkette("Hamstername (oder ende): ");
        if (name.equals("ende")) {
            ende = true;
        } else {
            Hamster hamster = hamsterVerzeichnis.get(name);
            if (hamster == null) {
                paul.schreib("Einen Hamster mit dem Namen "
                        + name + " gibt es nicht!");
            } else {
                String befehl = paul
                        .liesZeichenkette("Befehl: ");
                HamsterAktion aktion = aktionsVerzeichnis
                        .get(befehl);
                if (aktion == null) {
                    paul.schreib("Hamster kennen den Befehl " + befehl + " leider nicht!");
                } else {
                    aktion.ausfuehren(hamster);
                }
            }
        }
    }
}}