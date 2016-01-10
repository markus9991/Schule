import java.util.Random;


public class Batch {
	
	Card[] Karten=new Card[312];
	int zähler=0;
	int anzgezogenerkarten=0;
	int farben=4;
	int nummern=13;
	int anzDecks=6;
	
	public Batch(){
		for(int i=0;i<anzDecks;i++){
			for (int j = 0; j < farben; j++) {
				for (int k = 1; k <= nummern; k++) {
					Karten[zähler]=new Card(j, k);
					zähler++;
				}
				
			}
		}
	}
	
	public Card[] getKarten(){
		return this.Karten;
	}
	
	public Card karteZiehen(){		
		Random rnd = new Random();
			
			int zz = rnd.nextInt(Karten.length - anzgezogenerkarten);
			Card gezKarte = Karten[zz];
			Card x = Karten[Karten.length - 1 - anzgezogenerkarten];
			Karten[Karten.length - 1 - anzgezogenerkarten] = Karten[zz];
			Karten[zz] = x;
			anzgezogenerkarten++;
			return gezKarte;
			
		
	}

	public void reset() {
		zähler=0;
		for(int i=0;i<anzDecks;i++){
			for (int j = 0; j < farben; j++) {
				for (int k = 0; k < nummern; k++) {
					Karten[zähler]=new Card(j, k);
					zähler++;
				}
				
			}
		}	
	}

}
