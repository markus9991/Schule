import java.util.ArrayList;

public class BJLogik {

	public static int wertErmitteln(ArrayList<Card> karten, boolean croupier) {
		int asszaehler = 0;
		int wert = 0;
		for (Card k : karten) {
			switch (k.getValue()) {
			case 1:
				
				if(croupier==true){
					wert+=11;
				}else{wert+=1;}
				
				asszaehler++;
				break;
			case 11:
				wert += 10;
				break;
			case 12:
				wert += 10;
				break;
			case 13:
				wert += 10;
				break;
			default:
				wert += k.getValue();
			}
		}
		if (!croupier) {
			while (asszaehler != 0) {
				if (wert - 1 < 11) {
					wert += 11;
				} else {
					wert += 1;
				}
				asszaehler--;
			}
		}
		
		if (croupier) {
			while (asszaehler != 0) {
				if (wert > 21) {
					wert -= 10;
				}
				asszaehler--;
			}
		}
		
		return wert;
	}
	
	public static boolean KarteZiehen(boolean croupier,ArrayList<Card>karten) {
		
		if (wertErmitteln(karten,croupier)<17&&croupier==true){
			return true;
		}
		else if(wertErmitteln(karten,croupier)<16&&croupier==false){
			return true;
		}else{return false;}
	}
	
	public static ArrayList<Spieler> evaluiereGewinner(Spieler croupier,ArrayList<Spieler> mitspieler){
		
		ArrayList<Spieler> gewinner= new ArrayList<Spieler>();
		mitspieler.add(croupier);
		int siegerwert=0;
		
		Spieler x= null;
		for (int i = 1; i < mitspieler.size(); i++) {
			x = mitspieler.get(i);
			int j = i;
			while (j > 0 && mitspieler.get(j - 1).getWert() > x.getWert()) {
				mitspieler.set(j, mitspieler.get(j-1));
				j--;
			}
			mitspieler.set(j, x);
		}	
		for (Spieler s : mitspieler) {
			if(s.getWert()>siegerwert&&s.getWert()<22){
				siegerwert=s.getWert();
			}
		}
		for (Spieler s : mitspieler) {
			if(s.getWert()==siegerwert){
				gewinner.add(s);
			}
		}	
		return gewinner;
	}
}
