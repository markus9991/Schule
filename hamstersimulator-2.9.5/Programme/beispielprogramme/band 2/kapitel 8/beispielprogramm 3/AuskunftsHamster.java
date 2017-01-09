import de.hamster.debugger.model.Territorium;import de.hamster.debugger.model.Territory;import de.hamster.model.HamsterException;import de.hamster.model.HamsterInitialisierungsException;import de.hamster.model.HamsterNichtInitialisiertException;import de.hamster.model.KachelLeerException;import de.hamster.model.MauerDaException;import de.hamster.model.MaulLeerException;import de.hamster.model.MouthEmptyException;import de.hamster.model.WallInFrontException;import de.hamster.model.TileEmptyException;import de.hamster.debugger.model.Hamster;class AuskunftsHamster extends AllroundHamster {

    final static int ANZAHL = Territorium.getAnzahlReihen();

    boolean[][] verbindungen;

    AuskunftsHamster(int r, int s, int b) {
        super(r, s, b, AuskunftsHamster.ANZAHL
                * AuskunftsHamster.ANZAHL);
        // Hamster hat genuegend Koerner im Maul
        this.laufeZuKachel(0, 0);
        this.setzeBlickrichtung(Hamster.OST);
        this.verbindungen = new boolean[ANZAHL][ANZAHL];
    }

    void ermittleDirektVerbindungen() {
        for (int r = 0; r < AuskunftsHamster.ANZAHL; r++) {
            this.ermittle();
            for (int s = 0; s < AuskunftsHamster.ANZAHL - 1; s++) {
                this.vor();
                this.ermittle();
            }
            if (r % 2 == 0) {
                this.rechtsUm();
                if (this.vornFrei()) {
                    this.vor();
                    this.rechtsUm();
                }
            } else {
                this.linksUm();
                if (this.vornFrei()) {
                    this.vor();
                    this.linksUm();
                }
            }
        }
    }

    void ermittle() {
        if (this.kornDa()) {
            this.nimm();
            this.verbindungen[this.getReihe()][this.getSpalte()] = true;
        } else {
            this.verbindungen[this.getReihe()][this.getSpalte()] = false;
        }
    }

    void berechneAlleVerbindungen() {
        boolean geaendert;
        do {
            geaendert = false;
            for (int r = 0; r < AuskunftsHamster.ANZAHL; r++) {
                for (int s = 0; s < AuskunftsHamster.ANZAHL; s++) {
                    for (int k = 0; k < AuskunftsHamster.ANZAHL; k++) {
                        if (this.verbindungen[r][k]
                                && this.verbindungen[k][s]
                                && !this.verbindungen[r][s]) {
                            this.verbindungen[r][s] = true;
                            geaendert = true;
                        }
                    }
                }
            }
        } while (geaendert);
    }

    void gibAlleVerbindungenAus() {
        this.laufeZuKachel(0, 0);
        this.setzeBlickrichtung(Hamster.OST);
        for (int r = 0; r < AuskunftsHamster.ANZAHL; r++) {
            this.markiere();
            for (int s = 0; s < AuskunftsHamster.ANZAHL - 1; s++) {
                this.vor();
                this.markiere();
            }
            if (r % 2 == 0) {
                this.rechtsUm();
                if (this.vornFrei()) {
                    this.vor();
                    this.rechtsUm();
                }
            } else {
                this.linksUm();
                if (this.vornFrei()) {
                    this.vor();
                    this.linksUm();
                }
            }
        }
    }

    void markiere() {
        if (this.verbindungen[this.getReihe()][this.getSpalte()]) {
            this.gib();
        }
    }
}