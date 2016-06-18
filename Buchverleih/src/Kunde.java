import java.util.ArrayList;

public class Kunde extends DBObj {
	
	private int kundenNr;
	private String vorname;
	private String nachname;
	private String strasse;
	private int hausnummer;
	private String ort;
	private int plz;
	private ArrayList<Verleih> verleihe=new ArrayList<Verleih>();
	private char geschlecht;
	
	
	public Kunde(int kundenNr, String vorname, String nachname, String strasse, int hausnummer, String ort, int plz, char geschlecht,ArrayList<Verleih> verleihe) {
		this.kundenNr = kundenNr;
		this.vorname = vorname;
		this.nachname = nachname;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.ort = ort;
		this.plz = plz;
		this.verleihe = verleihe;
		this.geschlecht= geschlecht;
	}
	
	public char getGeschlecht() {
		return geschlecht;
	}

	@Override
	public void save(){
		DBManager.getDBManager().saveKunde(this);
	}
	
	public void delete(){
		DBManager.getDBManager().deleteKunde(this);
	}

	public int getKundenNr() {
		return kundenNr;
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public String getStrasse() {
		return strasse;
	}

	public int getHausnummer() {
		return hausnummer;
	}

	public String getOrt() {
		return ort;
	}

	public int getPlz() {
		return plz;
	}

	public ArrayList<Verleih> getVerleihe() {
		return verleihe;
	}

}
