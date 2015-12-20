import java.util.ArrayList;

public class Spiel {

	private boolean spielläuft = false;
	private boolean spielerwollenmehrkarten=false;
	private String name;
	private Spieler croupier;
	private ArrayList<Spieler> mitspieler = new ArrayList<Spieler>();
	private Batch batch;

	public Spiel(String name, Spieler croupier, Batch batch) {
		this.batch = batch;
		croupier.setBatch(this.batch);
		this.croupier=croupier;
		this.name = name;
		System.out.println("Das Spiel "+ this.name+ " wurde ergolgreich initialisiert und kann nun besetzt werden");
	}

	public void addSpieler(Spieler spieler) {
		if (this.spielläuft == false) {
			spieler.setBatch(this.batch);
			mitspieler.add(spieler);
		}
		else{
			System.out.println("Es können keine Spieler mehr beitreten,da das Spiel bereits begonnen hat!!!!");
		}
	}
	
	public void starteSpiel(){
		this.spielläuft=true;
		croupier.hitKarte();
		
		for (int i = 0; i < 2; i++) {
			for (Spieler s : mitspieler) {
				s.hitKarte();
			}
		}
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
		Spieler gewinner=null;
		if (croupier.getWert()<22) {
			gewinner=this.croupier;
		}
		else{
			gewinner=new Spieler("Dummie");
		}
		
		System.out.println("Der Croupier hat erreicht: "+croupier.getWert());
		for (Spieler s : mitspieler) {
			System.out.println("Spieler "+s.getName()+" hat errreicht: "+s.getWert());
			if(s.getWert()>gewinner.getWert()&&s.getWert()<22){
				gewinner=s;
			}
		}
			
		
		
		System.out.println("Und gewonnen hat: "+gewinner.getName());
	}

	public void beendeSpiel() {
		this.batch.reset();
		this.spielläuft=false;
		
	}

}
