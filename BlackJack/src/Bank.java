import java.util.ArrayList;


public class Bank {
	
	private ArrayList<Spieler> spieler=new ArrayList<Spieler>();
	private ArrayList<Integer> Einsätze=new ArrayList<Integer>();
	private int pot;
	
	public void beitreten(Spieler spieler,int Einsatz){
		this.spieler.add(spieler);
		this.Einsätze.add(Einsatz);
		
		
	}
	
	public void setzen(Spieler spieler,int höhe){

			
			int index=0;
			for (Spieler sp:this.spieler) {
				if(sp.equals(spieler)){
					break;
				}else{index+=1;}
			
			
			
			
				pot+=Einsätze.get(index);
			
		}
	}

}
