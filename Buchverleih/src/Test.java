import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args){
		DBManager dbm = DBManager.getDBManager();
		Kunde k = new Kunde(2000,"Maximilian","Mustermann","Mustergasse",12,"Musterort",1234,'m',new ArrayList<Verleih>());
		Buch b=new Buch(1003,"Inferno","Dan Brown","CBT","Thriller",new ArrayList<Verleih>());
		dbm.saveKunde(k);
		dbm.saveBuch(b);
		dbm.deleteBuch(b);
		ArrayList<Kunde>kunden=dbm.readKunde();
//		dbm.deleteKunde(k);
		
		for(Kunde ku:kunden){
			System.out.println(ku.toString());
		}
			
		dbm.close();
	}
	

}
