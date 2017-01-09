package de.hamster.interpreter;

import java.util.ArrayList;

import de.hamster.simulation.view.DialogTerminal;
import de.hamster.workbench.Workbench;

/**
 * 
 * @author Dietrich Boles (Universitaet Oldenburg)
 * @version 1.0 (25.01.2006)
 * 
 */
public class Hamster {

	static ArrayList _intern_hamsters = new ArrayListIntern();

	private static Hamster _intern_standardHamster = new Hamster(false);

	private static int _intern_ID = 0;

	/**
	 * Blickrichtung Nord
	 */
	public final static int NORD = 0;

	/**
	 * Blickrichtung Ost
	 */
	public final static int OST = 1;

	/**
	 * Blickrichtung Sued
	 */
	public final static int SUED = 2;

	/**
	 * Blickrichtung West
	 */
	public final static int WEST = 3;

	public static void _re_init() {
		ArrayList<Hamster> hams = new ArrayList<Hamster>();
		int no = _intern_hamsters.size();
		for (int i = 0; i < no; i++) {
			Hamster h = (Hamster) _intern_hamsters.get(i);
			if (h._intern_id != -1) {
				hams.add(h);
			}
		}
		no = hams.size();
		for (int i = 0; i < no; i++) {
			Hamster ham = hams.get(i);
			_intern_hamsters.remove(ham);
			Workbench.getWorkbench().getSimulationController()
					.getSimulationModel().removeHamster(ham._intern_id);
			ham._intern_init = false;
		}
		_intern_ID = 0;
		Workbench.getWorkbench().getSimulationController().getSimulationModel()
				.clearLogs(); // dibo 290710
	}

	/**
	 * Konstruktor zum Erzeugen eines nicht initialisierten Hamsters
	 */
	public Hamster() {
		this._intern_init = false;
	}

	/**
	 * Konstruktor zum Erzeugen und Initialisieren eines Hamsters mit den
	 * uebergebenen Parametern
	 * 
	 * @param reihe
	 *            die Reihe des Territoriums, in der der Hamster erzeugt wird
	 * @param spalte
	 *            die Spalte des Territoriums, in der der Hamster erzeugt wird
	 * @param blickrichtung
	 *            die Richtung, in der der Hamster anfangs schaut (siehe
	 *            Konstanten)
	 * @param anzahlKoerner
	 *            die Anzahl an Koernern, die der Hamster anfangs im Maul hat
	 * @throws HamsterInitialisierungsException
	 *             wird geworfen, wenn: (a) eine Kachel (reihe/spalte) nicht
	 *             existiert (b) die Kachel (reihe/spalte) durch eine Mauer
	 *             blockiert ist (c) der Wert von blickrichtung nicht zwischen 0
	 *             und 3 liegt (d) der Wert von anzahlKoerner < 0 ist
	 */
	public Hamster(int reihe, int spalte, int blickrichtung, int anzahlKoerner)
			throws HamsterInitialisierungsException {
		this.init(reihe, spalte, blickrichtung, anzahlKoerner);
	}

	/**
	 * Konstruktor zum Erzeugen und Initialisieren eines Hamsters mit den Werten
	 * eines bereis existierenden Hamsters
	 * 
	 * @param hamster
	 *            ein bereits existierender Hamster
	 */
	public Hamster(Hamster hamster) {
		if (hamster == null || !hamster._intern_init) {
			this._intern_init = false;
		} else {
			this.init(hamster.getReihe(), hamster.getSpalte(), hamster
					.getBlickrichtung(), hamster.getAnzahlKoerner());
		}
	}

	/**
	 * Methode zum Initialisieren eines noch nicht initialsierten Hamsters. Der
	 * Aufruf der Methode fuer einen bereits initialisierten Hamster bewirkt
	 * nichts.
	 * 
	 * @param reihe
	 *            die Reihe des Territoriums, in der der Hamster erzeugt wird
	 * @param spalte
	 *            die Spalte des Territoriums, in der der Hamster erzeugt wird
	 * @param blickrichtung
	 *            die Richtung, in der der Hamster anfangs schaut (siehe
	 *            Konstanten)
	 * @param anzahlKoerner
	 *            die Anzahl an Koernern, die der Hamster anfangs im Maul hat
	 * @throws HamsterInitialisierungsException
	 *             wird geworfen, wenn: (a) eine Kachel (reihe/spalte) nicht
	 *             existiert (b) die Kachel (reihe/spalte) durch eine Mauer
	 *             blockiert ist (c) der Wert von blickrichtung nicht zwischen 0
	 *             und 3 liegt (d) der Wert von anzahlKoerner < 0 ist
	 */
	public void init(int reihe, int spalte, int blickrichtung, int anzahlKoerner)
			throws HamsterInitialisierungsException {
		try {
			if (_intern_init)
				return;
			this._intern_init = false;
			if (this._intern_id == -2) {
				this._intern_id = _intern_ID++;
			}
			Workbench.getWorkbench().getSimulationController()
					.getSimulationModel().newHamster(this._intern_id, reihe,
							spalte, blickrichtung, anzahlKoerner, -1);
			this._intern_init = true;
			_intern_hamsters.add(this);
			Workbench
					.getWorkbench()
					.getSimulationController()
					.getLogPanel()
					.logEntry(
							"init(" + reihe + "," + spalte + ","
									+ blickrichtung + "," + anzahlKoerner + ")",
							"ok", true, this._intern_id);
		} catch (de.hamster.model.HamsterNichtInitialisiertException exc) {
			Workbench
					.getWorkbench()
					.getSimulationController()
					.getLogPanel()
					.logEntry(
							"init(" + reihe + "," + spalte + ","
									+ blickrichtung + "," + anzahlKoerner + ")",
							"HamsterInitialisierungsException()", true,
							this._intern_id);
			throw new HamsterInitialisierungsException(this);
		} finally {
			sleep();
		}
	}

	/**
	 * Der aufgerufene Hamster springt auf die in Blickrichtung vor ihm liegende
	 * Kachel.
	 * 
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 * @throws MauerDaException
	 *             wird geworfen, wenn die Kachel in Blickrichtung vor dem
	 *             Hamster durch eine Mauer blockiert ist oder der Hamster in
	 *             Blickrichtung am Rand des Territoriums steht
	 */
	public void vor() throws HamsterNichtInitialisiertException,
			MauerDaException {
		try {

			checkInit("vor()");

			Workbench.getWorkbench().getSimulationController()
					.getSimulationModel().forward(this._intern_id);
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("vor()", "ok", true, this._intern_id);
		} catch (de.hamster.model.HamsterNichtInitialisiertException exc) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("vor()", "HamsterNichtInitialisiertException()",
							true, this._intern_id);
			throw new HamsterNichtInitialisiertException(this);
		} catch (de.hamster.model.MauerDaException exc) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry(
							"vor()",
							"MauerDaException(" + exc.getReihe() + ","
									+ exc.getSpalte() + ")", true,
							this._intern_id);
			throw new MauerDaException(this, exc.getReihe(), exc.getSpalte());
		} finally {
			sleep();
		}
	}

	/**
	 * Der aufgerufene Hamster dreht sich linksum.
	 * 
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 */
	public void linksUm() throws HamsterNichtInitialisiertException {
		try {
			checkInit("linksUm()");
			Workbench.getWorkbench().getSimulationController()
					.getSimulationModel().turnLeft(this._intern_id);
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("linksUm()", "ok", true, this._intern_id);
		} catch (de.hamster.model.HamsterNichtInitialisiertException exc) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("linksUm()",
							"HamsterNichtInitialisiertException()", true,
							this._intern_id);
			throw new HamsterNichtInitialisiertException(this);
		} finally {
			sleep();
		}
	}

	/**
	 * Der aufgerufene Hamster legt ein Korn auf der Kachel ab, auf der er sich
	 * gerade befindet.
	 * 
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 * @throws MaulLeerException
	 *             wird geworfen, wenn der Hamster keine Koerner im Maul hat
	 */
	public void gib() throws HamsterNichtInitialisiertException,
			MaulLeerException {
		try {
			checkInit("gib()");
			Workbench.getWorkbench().getSimulationController()
					.getSimulationModel().layDown(this._intern_id);
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("gib()", "ok", true, this._intern_id);
		} catch (de.hamster.model.HamsterNichtInitialisiertException exc) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("gib()", "HamsterNichtInitialisiertException()",
							true, this._intern_id);
			throw new HamsterNichtInitialisiertException(this);
		} catch (de.hamster.model.MaulLeerException exc) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("gib()", "MaulLeerException()", true,
							this._intern_id);
			throw new MaulLeerException(this);
		} finally {
			sleep();
		}
	}

	/**
	 * Der aufgerufene Hamster frisst ein Korn auf der Kachel, auf der er sich
	 * gerade befindet.
	 * 
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 * @throws KachelLeerException
	 *             wird geworfen, wenn auf der Kachel, auf der sich der Hamster
	 *             gerade befindet, kein Korn liegt
	 */
	public void nimm() throws HamsterNichtInitialisiertException,
			KachelLeerException {
		try {
			checkInit("nimm()");
			Workbench.getWorkbench().getSimulationController()
					.getSimulationModel().pickUp(this._intern_id);
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("nimm()", "ok", true, this._intern_id);
		} catch (de.hamster.model.HamsterNichtInitialisiertException exc) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("nimm()", "HamsterNichtInitialisiertException()",
							true, this._intern_id);
			throw new HamsterNichtInitialisiertException(this);
		} catch (de.hamster.model.KachelLeerException exc) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry(
							"nimm()",
							"KachelLeerException(" + exc.getReihe() + ","
									+ exc.getSpalte() + ")", true,
							this._intern_id);
			throw new KachelLeerException(this, exc.getReihe(), exc.getSpalte());
		} finally {
			sleep();
		}
	}

	/**
	 * liefert genau dann true, wenn sich in Blickrichtung vor dem aufgerufenen
	 * Hamster keine Mauer befindet (wenn sich der Hamster in Blickrichtung am
	 * Rand des Territoriums befindet, wird false geliefert)
	 * 
	 * @return true, wenn sich in Blickrichtung vor dem aufgerufenen Hamster
	 *         keine Mauer befindet; sonst false
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 */
	public boolean vornFrei() throws HamsterNichtInitialisiertException {
		try {
			checkInit("vornFrei()");
			boolean res = Workbench.getWorkbench().getSimulationController()
					.getSimulationModel().free(this._intern_id);
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("vornFrei()", (res ? "true" : "false"), true,
							this._intern_id);
			return res;
		} catch (de.hamster.model.HamsterNichtInitialisiertException exc) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("vornFrei()",
							"HamsterNichtInitialisiertException()", true,
							this._intern_id);
			throw new HamsterNichtInitialisiertException(this);
		} finally {
			sleep();
		}
	}

	/**
	 * liefert genau dann true, wenn der aufgerufene Hamster keine Koerner im
	 * Maul hat
	 * 
	 * @return true, wenn der aufgerufene Hamster keine Koerner im Maul hat;
	 *         sonst false
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 */
	public boolean maulLeer() throws HamsterNichtInitialisiertException {
		try {
			checkInit("maulLeer()");
			boolean res = Workbench.getWorkbench().getSimulationController()
					.getSimulationModel().mouthEmpty(this._intern_id);
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("maulLeer()", (res ? "true" : "false"), true,
							this._intern_id);
			return res;
		} catch (de.hamster.model.HamsterNichtInitialisiertException exc) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("maulLeer()",
							"HamsterNichtInitialisiertException()", true,
							this._intern_id);
			throw new HamsterNichtInitialisiertException(this);
		} finally {
			sleep();
		}
	}

	/**
	 * liefert genau dann true, wenn auf der Kachel, auf der sich der
	 * aufgerufene Hamster gerade befindet, mindestens ein Korn liegt
	 * 
	 * @return true, wenn auf der Kachel, auf der sich der aufgerufene Hamster
	 *         gerade befindet, mindestens ein Korn liegt; sonst false
	 * @throws HamsterNichtInitialisiertException
	 */
	public boolean kornDa() throws HamsterNichtInitialisiertException {
		try {
			checkInit("kornDa()");
			boolean res = Workbench.getWorkbench().getSimulationController()
					.getSimulationModel().cornAvailable(this._intern_id);
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("kornDa()", (res ? "true" : "false"), true,
							this._intern_id);
			return res;
		} catch (de.hamster.model.HamsterNichtInitialisiertException exc) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("kornDa()",
							"HamsterNichtInitialisiertException()", true,
							this._intern_id);
			throw new HamsterNichtInitialisiertException(this);
		} finally {
			sleep();
		}
	}

	/**
	 * gibt den uebergebenen String (in einer Dialogbox) auf den Bildschirm aus
	 * 
	 * @param zeichenkette
	 *            der auszugebende String
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 */
	public void schreib(String zeichenkette)
			throws HamsterNichtInitialisiertException {
		try {
			checkInit("schreib(\"" + zeichenkette + "\")");
			DialogTerminal terminal = DialogTerminal.getInstance();
			terminal.write(this._intern_id, zeichenkette);
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("schreib(\"" + zeichenkette + "\")", "ok", true,
							this._intern_id);
		} finally {
			sleep();
		}
	}

	/**
	 * gibt den uebergebenen String auf den Bildschirm aus und fordert den
	 * Benutzer auf, einen String einzugeben; der eingegebene String wird als
	 * Wert geliefert
	 * 
	 * @param aufforderung
	 *            der auszugebende String
	 * @return der vom Benutzer eingegebene String
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 */
	public String liesZeichenkette(String aufforderung)
			throws HamsterNichtInitialisiertException {
		try {
			checkInit("liesZeichenkette(\"" + aufforderung + "\")");
			DialogTerminal terminal = DialogTerminal.getInstance();
			String res = terminal.readString(this._intern_id, aufforderung);
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("liesZeichenkette(\"" + aufforderung + "\")",
							res, true, this._intern_id);
			return res;
		} finally {
			sleep();
		}
	}

	/**
	 * gibt den uebergebenen String auf den Bildschirm aus und fordert den
	 * Benutzer auf, eine Zahl einzugeben; die eingegebene Zahl wird als Wert
	 * geliefert (wenn der Benutzer eine ungueltige Zahl eingibt, wird der Wert
	 * 0 geliefert)
	 * 
	 * @param aufforderung
	 *            der auszugebende String
	 * @return die vom Benutzer eingegebene Zahl
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch // nicht initialisiert
	 *             worden ist
	 */
	public int liesZahl(String aufforderung)
			throws HamsterNichtInitialisiertException {
		try {
			checkInit("liesZahl(\"" + aufforderung + "\")");
			DialogTerminal terminal = DialogTerminal.getInstance();
			int res = terminal.readInt(this._intern_id, aufforderung);
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("liesZahl(\"" + aufforderung + "\")", "" + res,
							true, this._intern_id);
			return res;
		} finally {
			sleep();
		}
	}

	/**
	 * liefert die Reihe der Kachel des Territoriums, auf der sich der
	 * aufgerufene Hamster gerade befindet
	 * 
	 * @return die Reihe der Kachel des Territoriums, auf der sich der
	 *         aufgerufene Hamster gerade befindet
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch // nicht initialisiert
	 *             worden ist
	 */
	public int getReihe() throws HamsterNichtInitialisiertException {
		try {
			checkInit("getReihe()");
			de.hamster.simulation.model.Hamster h = Workbench.getWorkbench()
					.getSimulationController().getSimulationModel().getHamster(
							this._intern_id);
			int res = h.getY();
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("getReihe()", "" + res, true, this._intern_id);
			return res;
		} finally {
			sleep();
		}
	}

	/**
	 * liefert die Spalte der Kachel des Territoriums, auf der sich der
	 * aufgerufene Hamster gerade befindet
	 * 
	 * @return die Spalte der Kachel des Territoriums, auf der sich der
	 *         aufgerufene Hamster gerade befindet
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 */
	public int getSpalte() throws HamsterNichtInitialisiertException {
		try {
			checkInit("getSpalte()");
			de.hamster.simulation.model.Hamster h = Workbench.getWorkbench()
					.getSimulationController().getSimulationModel().getHamster(
							this._intern_id);
			int res = h.getX();
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("getSpalte()", "" + res, true, this._intern_id);
			return res;
		} finally {
			sleep();
		}
	}

	/**
	 * liefert die Blickrichtung, in die der aufgerufene Hamster gerade schaut
	 * (die gelieferten Werte entsprechen den obigen Konstanten)
	 * 
	 * @return die Blickrichtung, in die der aufgerufene Hamster gerade schaut
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 */
	public int getBlickrichtung() throws HamsterNichtInitialisiertException {
		try {
			checkInit("getBlickrichtung()");
			de.hamster.simulation.model.Hamster h = Workbench.getWorkbench()
					.getSimulationController().getSimulationModel().getHamster(
							this._intern_id);
			int res = h.getDir();
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("getBlickrichtung()", "" + res, true,
							this._intern_id);
			return res;
		} finally {
			sleep();
		}
	}

	/**
	 * liefert die Anzahl der Koerner, die der aufgerufene Hamster gerade im
	 * Maul hat
	 * 
	 * @return die Anzahl der Koerner, die der aufgerufene Hamster gerade im
	 *         Maul hat
	 * @throws HamsterNichtInitialisiertException
	 *             wird geworfen, wenn der Hamster noch nicht initialisiert
	 *             worden ist
	 */
	public int getAnzahlKoerner() throws HamsterNichtInitialisiertException {
		try {
			checkInit("getAnzahlKoerner()");
			de.hamster.simulation.model.Hamster h = Workbench.getWorkbench()
					.getSimulationController().getSimulationModel().getHamster(
							this._intern_id);
			int res = h.getMouth();
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("getAnzahlKoerner()", "" + res, true,
							this._intern_id);
			return res;
		} finally {
			sleep();
		}
	}

	/**
	 * liefert den Standard-Hamster, das ist der Hamster, der sich
	 * standardmaessig im Territorium befindet, ohne explizit erzeugt werden zu
	 * muessen
	 * 
	 * @return der Standard-Hamster
	 */
	public static Hamster getStandardHamster() {
		Workbench.getWorkbench().getSimulationController().getLogPanel()
				.logEntry("getStandardHamster()", "hamster.Hamster", false, -2);
		return _intern_standardHamster;
	}

	public static Hamster getStandardHamsterIntern() {
		return _intern_standardHamster;
	}

	/**
	 * liefert die Gesamtzahl an erzeugten und initialisierten Hamstern im
	 * Territorium (inkl. dem Standard-Hamster)
	 * 
	 * @return die Gesamtzahl an erzeugten und initialisierten Hamstern im
	 *         Territorium
	 */
	public static int getAnzahlHamster() {
		int res = _intern_hamsters.size();
		Workbench.getWorkbench().getSimulationController().getLogPanel()
				.logEntry("getAnzahlHamster()", "" + res, false, -2);
		return res;
	}

	/**
	 * Methode, die einen Klon des aufgerufenen Hamsters erzeugt und liefert,
	 * d.h. die Werte der Attribute des neuen Hamsters sind identisch zu den
	 * Werten des aufgerufenen Hamsters. Wenn der aufgerufene Hamster noch nicht
	 * initialisiert ist, wird der neu erzeugte Hamster auch nicht initialisiert
	 * (ueberschreibt die entsprechende von der Klasse Object geerbte Methode).
	 * 
	 * @return ein Klon des aufgerufenen Hamsters
	 * @see java.lang.Object#clone()
	 */
	public Object clone() {
		Workbench.getWorkbench().getSimulationController().getLogPanel()
				.logEntry("clone()", "hamster.Hamster", true, this._intern_id);
		if (!this._intern_init) {
			return new Hamster();
		}
		return new Hamster(this);
	}

	/**
	 * Methode, die ueberprueft, ob die Werte der Attribute des aufgerufenen
	 * Hamsters gleich der Attributwerte des uebergebenen Hamsters sind (zwei
	 * nicht initialisierte Hamster sind auch gleich) (ueberschreibt die
	 * entsprechende von der Klasse Object geerbte Methode)
	 * 
	 * @param hamster
	 *            muss ein Objekt der Klasse Hamster oder einer davon
	 *            abgeleiteten Klasse sein
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	// public boolean equals(Object hamster) {
	// boolean res = false;
	// if (hamster == null || !(hamster instanceof Hamster)) {
	// res = false;
	// } else {
	// Hamster ham = (Hamster) hamster;
	// if (!ham._intern_init) {
	// res = this._intern_init == ham._intern_init;
	// } else {
	// de.hamster.simulation.model.Hamster hThis = _intern_simulationModel
	// .getHamster(this._intern_id);
	// de.hamster.simulation.model.Hamster hOther = _intern_simulationModel
	// .getHamster(ham._intern_id);
	// res = this._intern_init == ham._intern_init
	// && hThis.getY() == hOther.getY()
	// && hThis.getX() == hOther.getX()
	// && hThis.getDir() == hOther.getDir()
	// && hThis.getMouth() == hOther.getMouth();
	// }
	// }
	// _intern_logPanel.logEntry("equals(hamster.Hamster)", (res ? "true"
	// : "false"), true, this._intern_id);
	// return res;
	// }
	/**
	 * Methode, die eine String-Repraesentation der folgenden Art fuer den
	 * aufgerufenen Hamster liefert: "Hamster steht auf Kachel (0/0) mit
	 * Blickrichtung OST und 2 Koernern im Maul" Wenn der aufgerufene Hamster
	 * noch nicht initialisiert ist, wird folgender String geliefert: "Hamster
	 * ist nicht initialisiert" (ueberschreibt die entsprechende von der Klasse
	 * Object geerbte Methode)
	 * 
	 * @return eine String-Repraesentation des aktuellen Hamster-Zustands
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String res = "";
		if (!this._intern_init) {
			res = "Hamster ist nicht initialisiert";
		} else {
			int a = 0;
			String b = "";
			de.hamster.simulation.model.Hamster hThis = Workbench
					.getWorkbench().getSimulationController()
					.getSimulationModel().getHamster(this._intern_id);
			switch (hThis.getDir()) {
			case NORD:
				b = "NORD";
				break;
			case SUED:
				b = "SUED";
				break;
			case OST:
				b = "OST";
				break;
			case WEST:
				b = "WEST";
				break;
			}

			res = "Hamster steht auf Kachel " + "(" + hThis.getY() + "/"
					+ hThis.getX() + ") mit Blickrichtung " + b + " und "
					+ (a = hThis.getMouth()) + (a == 1 ? " Korn" : " Koernern")
					+ " im Maul";
		}
		Workbench.getWorkbench().getSimulationController().getLogPanel()
				.logEntry("toString()", res, true, this._intern_id);
		return res;
	}

	/**
	 * Der aufgerufene Hamster wird geloescht, d.h. er wird vom Bildschirm
	 * entfernt und seine Initialisierung wird rueckgaengig gemacht. Durch
	 * Aufruf der init-Methode kann der Hamster wieder reanimiert werden. Wird
	 * die Methode fuer den Standard-Hamster aufgerufen, passiert nichts.
	 * 
	 * Achtung: Die Methode gehoert nicht zum Standard-Hamster-Modell!
	 * 
	 */
	public void loeschen() {
		try {

			if (this == _intern_standardHamster || this._intern_init == false) {
				return;
			}
			_intern_hamsters.remove(this);
			Workbench.getWorkbench().getSimulationController()
					.getSimulationModel().removeHamster(this._intern_id);
			this._intern_init = false;
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry("loeschen()", "ok", true, this._intern_id);
		} finally {
			sleep();
		}
	}

	// private internal members

	private int _intern_id = -2;

	private boolean _intern_init = false;

	private void checkInit(String befehl)
			throws HamsterNichtInitialisiertException {
		if (!this._intern_init) {
			Workbench.getWorkbench().getSimulationController().getLogPanel()
					.logEntry(befehl, "HamsterNichtInitialisiertException()",
							true, this._intern_id);
			throw new HamsterNichtInitialisiertException(this);
		}
	}

	protected Hamster(boolean dummy) {
		this._intern_init = true;
		this._intern_id = -1;
		_intern_hamsters.add(this);
	}

	public void sleep() {
		int delay = Workbench.getWorkbench().getDebugger().getDebuggerModel()
				.getDelay() / 3;
		try {
			Thread.sleep(delay);
		} catch (InterruptedException exc) {
			Thread.currentThread().interrupt();
		}
	}

	static class ArrayListIntern extends ArrayList {
		public boolean remove(Object obj) {
			for (int i = 0; i < this.size(); i++) {
				Object elem = this.get(i);
				if (elem == obj) {
					this.remove(i);
				}
			}
			return true;
		}
	}

}