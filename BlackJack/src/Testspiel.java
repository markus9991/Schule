import java.util.ArrayList;


public class Testspiel {

	public static void main(String[] args) {
		
		Batch b=new Batch();
		Spieler c=new Spieler("Croupier");
		Spiel spiel1=new Spiel("HW4a", c, b);
		Spieler s1=new Spieler("Markus");
		Spieler s2=new Spieler("Johannes");
		Spieler s3=new Spieler("Marcöl");
		Spieler s4=new Spieler("Simon");
		Spieler s5=new Spieler("Emir");
		
		spiel1.addSpieler(s1);
		spiel1.addSpieler(s2);
		spiel1.addSpieler(s3);
		spiel1.addSpieler(s4);
		spiel1.addSpieler(s5);
		
		spiel1.starteSpiel();
		System.out.println(spiel1.printSpiel());
		spiel1.spielerziehenKarten();
		spiel1.croupierZiehtKarten();
		System.out.println(spiel1.printSpiel());
		spiel1.auswerten();
		spiel1.beendeSpiel();
		
		

		
		

	}

}
