import java.util.ArrayList;

public class Spieler {

	private String name;
	private ArrayList<Card> karten = new ArrayList<Card>();
	private Batch batch;
	private boolean croupier=false;

	public Spieler(String name) {
		this.name = name;
	}

	public void setCroupier(){
		this.croupier=true;
	}
	
	public boolean hitKarte() {
		boolean sollZiehen= BJLogik.KarteZiehen(croupier, karten);
		if(sollZiehen==true){
			karten.add(batch.karteZiehen());
			return true;
		}else{return false;}
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public int getWert() {
		return BJLogik.wertErmitteln(karten,croupier);
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
