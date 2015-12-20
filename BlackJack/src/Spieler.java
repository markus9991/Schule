import java.util.ArrayList;

public class Spieler {

	private String name;
	private ArrayList<Card> karten = new ArrayList<Card>();
	private Batch batch;

	public Spieler(String name) {
		this.name = name;
	}

	public boolean hitKarte() {
		if (this.getWert() < 17) {
			karten.add(batch.karteZiehen());
			return true;
		} else {
			return false;
		}
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public int getWert() {
		int wert = 0;
		for (Card k : karten) {
			switch(k.getValue()){
			case 11: 
				wert+=10;
				break;
			case 12: 
				wert+=10;
				break;
			case 13: 
				wert+=10;
				break;
			default:
				wert += k.getValue();
			}
			
		}
		return wert;
	}

	public String printHand() {
		String blatt="";

		for (Card k : karten) {
			blatt += k.toString();
		}

		return String.format("Der Spieler %s hat folgendes Blatt:\n%s",
				this.name, blatt);
	}

	public String getName() {
		return this.name;
	}

}
