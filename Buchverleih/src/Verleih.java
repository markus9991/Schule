import java.util.Date;

public class Verleih extends DBObj{
	private int verleihNr;
	private int buchId;
	private int kundenNr;
	private Date von;
	private Date bis;
	private Kunde refKunde;
	private Buch refBuch;
	
	public Verleih(int verleihnr, int buchid, int kundennr, Date von, Date bis, Kunde refkunde, Buch refbuch){
		this.verleihNr = verleihnr;
		this.buchId = buchid;
		this.kundenNr = kundennr;
		this.von = von;
		this.bis = bis;
		this.refKunde = refkunde;
		this.refBuch = refbuch;
		
	}
	
	public void save(){
		DBManager.getDBManager().saveVerleih(this);
	}
	
	public void delete(){
		DBManager.getDBManager().deleteVerleih(this);
	}
	
	/*public void read(){
		DBManager.getDBManager().readVerleih(this);
	}*/
	
	public int getVerleihNr() {
		return verleihNr;
	}
	public int getBuchId() {
		return buchId;
	}
	public int getKundenNr() {
		return kundenNr;
	}
	public Date getVon() {
		return von;
	}
	public Date getBis() {
		return bis;
	}
	public Kunde getRefKunde() {
		return refKunde;
	}
	public Buch getRefBuch() {
		return refBuch;
	}
	
	

}
