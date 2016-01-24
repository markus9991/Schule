import java.util.ArrayList;

public class Spiel {

	private boolean spielläuft = false;
	private boolean spielerwollenmehrkarten=false;
	private String name;
	private Spieler croupier;
	private ArrayList<Spieler> mitspieler = new ArrayList<Spieler>();
	private Batch batch;
	private Bank bank;

	public Spiel(String name, Spieler croupier, Batch batch,Bank bank) {
		this.bank=bank;
		this.batch = batch;
		croupier.setBatch(this.batch);
		croupier.setCroupier();
		this.croupier=croupier;
		this.name = name;
		System.out.println("Das Spiel "+ this.name+ " wurde ergolgreich initialisiert und kann nun besetzt werden");
	}

	public void addSpieler(Spieler spieler,int Einsatz) {
		if (this.spielläuft == false) {
			spieler.setBatch(this.batch);
			mitspieler.add(spieler);
		}
		else{
			System.out.println("Es können keine Spieler mehr beitreten,da das Spiel bereits begonnen hat!!!!");
		}
	}
	
	public void starteRunde(){
		this.spielläuft=true;
		croupier.hitKarte();
		
		for (int i = 0; i < 2; i++) {
			for (Spieler s : mitspieler) {
				s.hitKarte();
			}
		}
	}
	
	public void spielerSetzen(){
		
	}
	
	public void spielerziehenKarten(){
		do{
			spielerwollenmehrkarten=false;
			for (Spieler s : mitspieler) {
				if(s.hitKarte()==true){
					spielerwollenmehrkarten=true;
				}
			}
		}while(spielerwollenmehrkarten==true);
	}
	
	public void croupierZiehtKarten(){
		do{	
		}while(croupier.hitKarte());
	}
	
	public String printSpiel(){
		String s="";
		s+=croupier.printHand();
		for (Spieler sp : mitspieler) {
			s+=sp.printHand();
		}
		return s;
	}

	public void auswerten() {
		
		for (Spieler sp : mitspieler) {
			System.out.println(sp.getName()+": "+sp.getWert());
		}
		System.out.println(croupier.getName()+": "+croupier.getWert());
		
		ArrayList<Spieler> gewinner=BJLogik.evaluiereGewinner(croupier, mitspieler);
		System.out.println("Und gewonnen hat: ");
		for (Spieler g : gewinner) {
			System.out.println(g.getName());
		}
	}

	public void beendeRunde() {
		this.batch.reset();
		this.spielläuft=false;
		
	}

}
