import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DBManager {
	
	private static DBManager instance;
	private Connection c;
	
	private DBManager() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		 c =DriverManager.getConnection("jdbc:mysql://localhost/buchverleih","root","");
	}
	
	public static DBManager getDBManager(){
		if(instance==null)
			try {
				instance=new DBManager();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return instance;
	}

	public void saveKunde(Kunde kunde) {
		ResultSet rs;
		String checkid ="Select * FROM Kunde WHERE KundenNr="+kunde.getKundenNr();
		String insertstatement="INSERT INTO Kunde VALUES("+kunde.getKundenNr()+",'"+kunde.getVorname()+"','"+kunde.getNachname()+"','"+kunde.getStrasse()+"','"+kunde.getHausnummer()+"','"+kunde.getOrt()+"','"+kunde.getPlz()+"','"+kunde.getGeschlecht()+"'";
		String upadatestatement="UPDATE Kunde SET Vorname='"+kunde.getVorname()+"', Nachname='"+kunde.getNachname()+"', Straße='"+kunde.getStrasse()+"', Hausnummer='"+kunde.getHausnummer()+"', Ort='"+kunde.getOrt()+"', PLZ='"+kunde.getPlz()+"', Geschlecht='"+kunde.getGeschlecht()+"' WHERE KundenNr="+kunde.getKundenNr();
		String test="INSERT INTO Kunde VALUES(2000,'Max','Mustermann','Mustergasse',12,'Musterort',1234,'m');";
		try {
			java.sql.Statement stmt = c.createStatement();
			rs = stmt.executeQuery(checkid);
			if(rs.next() == false){
				stmt.executeUpdate(insertstatement);
				System.out.println("Successfully inserted Kunde");
			}
			else{
				System.out.println("else ausgelöst");
				stmt.executeUpdate(upadatestatement);
				System.out.println("Successfully updated Kunde");				
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteKunde(Kunde kunde) {
		String deletestatement="DELETE FROM kunde WHERE KundenNr="+kunde.getKundenNr();
		
		try {
			java.sql.Statement stmt = c.createStatement();
			stmt.executeUpdate(deletestatement);
			System.out.println("Succesfully deleted Kunde");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void readKunde() {
		String readstatement="Select * FROM Kunde";
		try {
			java.sql.Statement stmt = c.createStatement();
			stmt.executeQuery(readstatement);
			System.out.println("Kunden:");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void readKunde(Buch buch) {
		String readstatement = "SELECT KundenNr FROM Verleih JOIN Kunde  WHERE KundenNR = "+buch.getBuchId();
		try {
			java.sql.Statement stmt = c.createStatement();
			stmt.executeQuery(readstatement);
			System.out.println("Kunden, die"+buch.getBuchId()+"ausgeliehen haben:");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveBuch(Buch buch) {
		ResultSet rs;
		String checkid ="Select * FROM Buch WHERE BuchId="+buch.getBuchId();
		String insertstatement="INSERT INTO Buch VALUES("+buch.getBuchId()+",'"+buch.getTitel()+"','"+buch.getAutor()+"','"+buch.getVerlag()+"','"+buch.getGenre()+"'";
		String upadatestatement="UPDATE Kunde SET Titel='"+buch.getTitel()+"', Autor='"+buch.getAutor()+"', Verlag='"+buch.getVerlag()+"', Genre='"+buch.getGenre();
		try {
			java.sql.Statement stmt = c.createStatement();
			rs = stmt.executeQuery(checkid);
			if(rs.next() == false){
				stmt.executeUpdate(insertstatement);
				System.out.println("Successfully inserted Buch");
			}
			else{
				System.out.println("else ausgelöst");
				stmt.executeUpdate(upadatestatement);
				System.out.println("Successfully updated Buch");				
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteBuch(Buch buch) {
		String deletestatement="DELETE FROM Buch WHERE BuchId="+buch.getBuchId();
		
		try {
			java.sql.Statement stmt = c.createStatement();
			stmt.executeUpdate(deletestatement);
			System.out.println("Succesfully deleted Buch");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void readBuch() {
		String readstatement="Select * FROM Buch";
		try {
			java.sql.Statement stmt = c.createStatement();
			stmt.executeQuery(readstatement);
			System.out.println("Bücher:");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void readBuch(Kunde kunde) {
		String readstatement = "Select BuchId FROM Verleih JOIN Kunde  WHERE KundenNR = "+kunde.getKundenNr();  
		try {
			java.sql.Statement stmt = c.createStatement();
			stmt.executeQuery(readstatement);
			System.out.println("Bücher, die"+kunde.getKundenNr()+"ausgeliehen hat:");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveVerleih(Verleih verleih) {
		ResultSet rs;
		String checkid ="Select * FROM Verleih WHERE VerleihNr="+verleih.getVerleihNr();
		String insertstatement="INSERT INTO Kunde VALUES("+verleih.getVerleihNr()+",'"+verleih.getBuchId()+",'"+verleih.getKundenNr()+"','"+verleih.getVon()+"','"+verleih.getBis()+"'";
		String upadatestatement="UPDATE Kunde SET BuchId='"+verleih.getBuchId()+"', KundenNr='"+verleih.getKundenNr()+"', Von='"+verleih.getVon()+"', Bis='"+verleih.getBis();
		try {
			java.sql.Statement stmt = c.createStatement();
			rs = stmt.executeQuery(checkid);
			if(rs.next() == false){
				stmt.executeUpdate(insertstatement);
				System.out.println("Successfully inserted Verleih");
			}
			else{
				System.out.println("else ausgelöst");
				stmt.executeUpdate(upadatestatement);
				System.out.println("Successfully updated Verleih");				
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteVerleih(Verleih verleih) {
		String deletestatement="DELETE FROM buch WHERE VerleihNr="+verleih.getVerleihNr();
		
		try {
			java.sql.Statement stmt = c.createStatement();
			stmt.executeUpdate(deletestatement);
			System.out.println("Succesfully deleted Verleih");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}




}
