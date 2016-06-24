import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
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
			String pstr="Insert into Kunde values(?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pstmt=c.prepareStatement(pstr);
			pstmt.setInt(1,kunde.getKundenNr());
			pstmt.setString(2, kunde.getVorname());
			pstmt.setString(3, kunde.getNachname());
			pstmt.setString(4, kunde.getStrasse());
			pstmt.setInt(5, kunde.getHausnummer());
			pstmt.setString(6, kunde.getOrt());
			pstmt.setInt(7, kunde.getPlz());
			pstmt.setString(8, String.valueOf(kunde.getGeschlecht()));
			
			
			java.sql.Statement stmt = c.createStatement();
			rs = stmt.executeQuery(checkid);
			if(rs.next() == false){
//				stmt.executeUpdate(insertstatement);
				pstmt.executeUpdate();
				System.out.println("Successfully inserted Kunde");
			}
			else{
				stmt.executeUpdate(upadatestatement);
				System.out.println("Successfully updated Kunde");				
		}
			rs.close();
			stmt.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteKunde(Kunde kunde) {
		String deletestatement="DELETE FROM kunde WHERE KundenNr="+kunde.getKundenNr();
		
		try {
			java.sql.Statement stmt = c.createStatement();
			stmt.executeUpdate(deletestatement);
			stmt.close();
			System.out.println("Succesfully deleted Kunde");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<Kunde> readKunde() {
		ArrayList<Kunde> kunden = new ArrayList<Kunde>();

		String readstatement="Select * FROM Kunde";
		try {
			
			java.sql.Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(readstatement);
			while(rs.next()){
				int kundenNr = rs.getInt("KundenNr");
				String vorname = rs.getString("Vorname");
				String nachname = rs.getString("Nachname");
				String strasse = rs.getString("Straße");
				int hausnummer = rs.getInt("Hausnummer");
				String ort = rs.getString("Ort");
				int plz = rs.getInt("PLZ");
				char geschlecht = rs.getString("Geschlecht").charAt(0);
				kunden.add(new Kunde(kundenNr, vorname, nachname, strasse, hausnummer, ort,plz, geschlecht, new ArrayList<Verleih>()));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully alle Kunden gelesen");
		return kunden;
	}
	
	public ArrayList<Kunde> readKunde(Buch buch) {
		ArrayList<Kunde> kunden = new ArrayList<Kunde>();
		String readstatement = "SELECT KundenNr FROM Verleih JOIN Kunde  WHERE KundenNR = "+buch.getBuchId();
		try {
			java.sql.Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(readstatement);
			while(rs.next()){
				int kundenNr = rs.getInt("KundenNr");
				String vorname = rs.getString("Vorname");
				String nachname = rs.getString("Nachname");
				String strasse = rs.getString("Straße");
				int hausnummer = rs.getInt("Hausnummer");
				String ort = rs.getString("Ort");
				int plz = rs.getInt("PLZ");
				char geschlecht = rs.getString("Geschlecht").charAt(0);
				kunden.add(new Kunde(kundenNr, vorname, nachname, strasse, hausnummer, ort,plz, geschlecht, new ArrayList<Verleih>()));
				
				stmt.close();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kunden;
	}

	public void saveBuch(Buch buch) {
		ResultSet rs;
		String checkid ="Select * FROM Buch WHERE BuchId="+buch.getBuchId();
		String insertstatement="INSERT INTO Buch VALUES("+buch.getBuchId()+",'"+buch.getTitel()+"','"+buch.getAutor()+"','"+buch.getVerlag()+"','"+buch.getGenre()+"'";
		String upadatestatement="UPDATE Buch SET Titel='"+buch.getTitel()+"', Autor='"+buch.getAutor()+"', Verlag='"+buch.getVerlag()+"', Genre='"+buch.getGenre()+"'";
		try {
			String pstr="Insert into Buch values(?,?,?,?,?)";
			java.sql.PreparedStatement pstmt =c.prepareStatement(pstr);
			pstmt.setInt(1, buch.getBuchId());
			pstmt.setString(2, buch.getTitel());
			pstmt.setString(3, buch.getAutor());
			pstmt.setString(4, buch.getVerlag());
			pstmt.setString(5, buch.getGenre());
			java.sql.Statement stmt = c.createStatement();
			rs = stmt.executeQuery(checkid);
			if(rs.next() == false){
//				stmt.executeUpdate(insertstatement);
				pstmt.executeUpdate();
				System.out.println("Successfully inserted Buch");
			}
			else{
				System.out.println("else ausgelöst");
				stmt.executeUpdate(upadatestatement);
				System.out.println("Successfully updated Buch");				
		}
			stmt.close();
			pstmt.close();
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
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public ArrayList<Buch> readBuch() {
		ArrayList<Buch> buecher = new ArrayList<Buch>();
		String readstatement="Select * FROM Buch";
		try {
			java.sql.Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(readstatement);
			while(rs.next()){
				int buchId = rs.getInt("BuchId");
				String titel = rs.getString("Titel");
				String autor = rs.getString("Autor");
				String verlag = rs.getString("Verlag");
				String genre = rs.getString("Genre");
				buecher.add(new Buch(buchId, titel, autor, verlag, genre, new ArrayList<Verleih>()));
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buecher;
	}
	
	public ArrayList<Buch> readBuch(Kunde kunde) {
		ArrayList<Buch> buecher = new ArrayList<Buch>();
		String readstatement = "Select BuchId FROM Verleih JOIN Kunde  WHERE KundenNR = "+kunde.getKundenNr();  
		try {
			java.sql.Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(readstatement);
			while(rs.next()){
				int buchId = rs.getInt("BuchId");
				String titel = rs.getString("Titel");
				String autor = rs.getString("Autor");
				String verlag = rs.getString("Verlag");
				String genre = rs.getString("Genre");
				buecher.add(new Buch(buchId, titel, autor, verlag, genre, new ArrayList<Verleih>()));
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buecher;
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
				stmt.close();
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
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	public void close(){
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
