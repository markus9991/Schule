import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args){
		DBManager dbm = DBManager.getDBManager();
		Kunde k = new Kunde(2000,"Maximilian","Mustermann","Mustergasse",12,"Musterort",1234,'m',new ArrayList<Verleih>());
		dbm.saveKunde(k);
		dbm.deleteKunde(k);
		
	}
	

}
