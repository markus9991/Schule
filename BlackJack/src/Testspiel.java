import java.util.ArrayList;


public class Testspiel {

	public static void main(String[] args) {
		
		Batch batch=new Batch();
		Bank bank=new Bank();
		
//		Card[] K=b.getKarten();
//		
//		for (Card k : K) {
//			System.out.println(k);
//		}
//		
//		ArrayList<Card> karten=new ArrayList<Card>();
//		karten.add(new Card(1, 6));
//		karten.add(new Card(1, 11));
//	//	karten.add(new Card(1, 1));
//		
//		System.out.println(BJLogik.wertErmitteln(karten));
//		System.out.println(BJLogik.KarteZiehen(false, karten));
//		System.out.println(BJLogik.KarteZiehen(true, karten));
		
		Spieler c=new Spieler("Croupier");
		Spiel spiel1=new Spiel("HW4a", c, batch,bank);
		Spieler s1=new Spieler("Markus");
		Spieler s2=new Spieler("Johannes");
		Spieler s3=new Spieler("Marcöl");
		Spieler s4=new Spieler("Simon");
		Spieler s5=new Spieler("Emir");
		
		spiel1.addSpieler(s1,1000);
		spiel1.addSpieler(s2,500);
		spiel1.addSpieler(s3,800);
		spiel1.addSpieler(s4,200);
		spiel1.addSpieler(s5,1200);
		
		spiel1.starteRunde();
		System.out.println(spiel1.printSpiel());
		spiel1.spielerziehenKarten();
		spiel1.croupierZiehtKarten();
		System.out.println(spiel1.printSpiel());
		
		
		
		spiel1.auswerten();
		spiel1.beendeRunde();
		
		

		
		

	}

}
