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

			
			int index=0;
			for (Spieler sp:this.spieler) {
				if(sp.equals(spieler)){
					break;
				}else{index+=1;}
			
			
			
			
				pot+=Eins�tze.get(index);
			
		}
	}

}
