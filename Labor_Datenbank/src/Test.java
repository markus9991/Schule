import java.sql.SQLException;

public class Test {
	
	public static void main(String[] args){
		
		long dauer1m=0;
		long dauer2m=0;
		long dauer3m=0;
		long dauer4m=0;
		
		long dauer1s=0;
		long dauer2s=0;
		long dauer3s=0;
		long dauer4s=0;
		
		
		try {
			DBM dbm=new DBM();
			//dbm.createDatabase();
			//dbm.datenEinfügen();
			for( int i=0;i<10;i++){
				System.out.println("================================================================================"+i);
				dbm.eintraegeLoeschen("Kopie1");
				dbm.eintraegeLoeschen("Kopie2");
				dbm.eintraegeLoeschen("Kopie3");
				dbm.eintraegeLoeschen("Kopie4");
			
				dauer1m+=dbm.kopie1();
				dauer2m+=dbm.kopie2();
				dauer3m+=dbm.kopie3();
				dauer4m+=dbm.kopie4();
			
			}
			dbm.closeConnection();
			
			
			
			DBS dbs=new DBS();
//			dbs.createDatabase();
//			dbs.datenEinfügen();
			for( int i=0;i<10;i++){
				System.out.println("================================================================================"+i);
				dbs.eintraegeLoeschen("Kopie1");
				dbs.eintraegeLoeschen("Kopie2");
				dbs.eintraegeLoeschen("Kopie3");
				dbs.eintraegeLoeschen("Kopie4");
			
				dauer1s+=dbs.kopie1();
				dauer2s+=dbs.kopie2();
				dauer3s+=dbs.kopie3();
				dauer4s+=dbs.kopie4();
			
			}
			dbs.closeConnection();
			
			System.out.println("================================================================================");
			System.out.println(dauer1m+"|"+dauer2m+"|"+dauer3m+"|"+dauer4m);
			System.out.println(dauer1s+"|"+dauer2s+"|"+dauer3s+"|"+dauer4s);
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
