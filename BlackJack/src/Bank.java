import java.util.ArrayList;


public class Bank {
	
	private ArrayList<Spieler> spieler=new ArrayList<Spieler>();
	private ArrayList<Integer> Eins�tze=new ArrayList<Integer>();
	private int pot;
	
	public void beitreten(Spieler spieler,int Einsatz){
		this.spieler.add(spieler);
		this.Eins�tze.add(Einsatz);
		
		
	}
	
	public void setzen(Spieler spieler,int h�he){
		for (Spieler s:this.spieler) {
			if(this.spieler.equals(spieler)){
			}
		}
	}

}
