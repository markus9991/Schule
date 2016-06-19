import java.util.ArrayList;

public class Buch extends DBObj{
	private int buchId;
	private String titel;
	private String autor;
	private String verlag;
	private String genre;
	private ArrayList<Verleih> verleihe=new ArrayList<Verleih>();
	
	public ArrayList<Verleih> getVerleihe() {
		return verleihe;
	}

	public Buch(int buchnr, String titel, String autor, String verlag, String genre, ArrayList<Verleih> verleihe){
		this.buchId = buchnr;
		this.titel = titel;
		this.autor = autor;
		this.verlag = verlag;
		this.genre = genre;
		this.verleihe = verleihe;
	}
	
	public void save(){
		DBManager.getDBManager().saveBuch(this);
	}
	
	public void delete(){
		DBManager.getDBManager().deleteBuch(this);
	}
	
	public int getBuchId() {
		return buchId;
	}

	public String getTitel() {
		return titel;
	}

	public String getAutor() {
		return autor;
	}

	public String getVerlag() {
		return verlag;
	}

	public String getGenre() {
		return genre;
	}
	

}
