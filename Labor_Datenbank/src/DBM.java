import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBM {
	
	Connection c;
	
	public DBM() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		 c =DriverManager.getConnection("jdbc:mysql://localhost/Labor_Test","root","");
		 System.out.println("Connection geöffnet");
	}
	
	public void createDatabase() throws SQLException{
		
		String createDBString="CREATE DATABASE IF NOT EXISTS Labor_Test";
		String createVorlage="CREATE TABLE Vorlage(intz INT,PRIMARY KEY(intz))";
		String createKopie1="CREATE TABLE Kopie1(intz INT,PRIMARY KEY(intz))";
		String createKopie2="CREATE TABLE Kopie2(intz INT,PRIMARY KEY(intz))";
		String createKopie3="CREATE TABLE Kopie3(intz INT,PRIMARY KEY(intz))";
		String createKopie4="CREATE TABLE Kopie4(intz INT,PRIMARY KEY(intz))";
		Statement stmt=c.createStatement();
		
	//	stmt.executeUpdate(createDBString);
		stmt.executeUpdate(createVorlage);
		stmt.executeUpdate(createKopie1);
		stmt.executeUpdate(createKopie2);
		stmt.executeUpdate(createKopie3);
		stmt.executeUpdate(createKopie4);
		stmt.close();
		System.out.println("done");
		
	}
	
	public void datenEinfügen() throws SQLException{
		
		String sql="INSERT INTO Vorlage VALUES (?)";
		
		PreparedStatement pstmt=c.prepareStatement(sql);
		
		for(int i=1; i<1001;i++){
			pstmt.setInt(1, i);
			pstmt.executeUpdate();
		}
		System.out.println("befüllt");
	}
	
	public void eintraegeLoeschen(String tabelle) throws SQLException{
		String s="DELETE FROM "+tabelle;
		Statement stmt=c.createStatement(); 
		stmt.executeUpdate(s);
		System.out.println("Einträge von "+tabelle+" gelöscht");
	}
	
	public long kopie1() throws SQLException{
	
		System.out.println("Kopiervorgang 1 gestartet");
		
		String sql1="INSERT INTO Kopie1 SELECT * FROM VORlaGE";
		Statement stmt2=c.createStatement();
		
		long lbevor=System.currentTimeMillis();
		stmt2.executeUpdate(sql1);
		long ldanach=System.currentTimeMillis();
		
		long dauer=ldanach-lbevor;
		System.out.println("Zeitdauer Kopie1: "+dauer+"ms");
		
		stmt2.close();
		return dauer;
	}
	
	public long kopie2() throws SQLException{
		System.out.println("Kopiervorgang 2 gestartet");
		
		String sql1="SELECT * FROM VORlaGE";
		Statement stmt2=c.createStatement();
		
		long lbevor=System.currentTimeMillis();
		ResultSet rs=stmt2.executeQuery(sql1);
		
		while(rs.next()){
			int i=rs.getInt(1);
			Statement stmt3=c.createStatement();
			String s="INSERT INTO Kopie2 VALUES ("+i+")";
			stmt3.executeUpdate(s);
			stmt3.close();
		}
		long ldanach=System.currentTimeMillis();
		
		long dauer=ldanach-lbevor;
		System.out.println("Zeitdauer Kopie2: "+dauer+"ms");
		
		rs.close();
		stmt2.close();
		return dauer;
	}
	
	public long kopie3() throws SQLException{
		System.out.println("Kopiervorgang 3 gestartet");
		
		String sql1="SELECT * FROM VORlaGE";
		PreparedStatement pstmt=c.prepareStatement(sql1);
		
		long lbevor=System.currentTimeMillis();
		ResultSet rs=pstmt.executeQuery();
		
		String sql="INSERT INTO Kopie3 VALUES (?)";
		PreparedStatement pstmt2=c.prepareStatement(sql);
		
		while(rs.next()){
			int i=rs.getInt(1);
			
			pstmt2.setInt(1, i);
			pstmt2.executeUpdate();
			
		}
		pstmt2.close();
		rs.close();
		pstmt.close();
		long ldanach=System.currentTimeMillis();
		
		long dauer=ldanach-lbevor;
		System.out.println("Zeitdauer Kopie 3: "+dauer+"ms");
		
		return dauer;
		
	}
	
	public long kopie4() throws SQLException{
		System.out.println("Kopiervorgang 4 gestartet");
		
		String sql1="SELECT * FROM VORlaGE";
		PreparedStatement pstmt=c.prepareStatement(sql1);
		
		long lbevor=System.currentTimeMillis();
		ResultSet rs=pstmt.executeQuery();
		
		String sql="INSERT INTO Kopie4 VALUES (?)";
		PreparedStatement pstmt2=c.prepareStatement(sql);
		
		while(rs.next()){
			int i=rs.getInt(1);
			
			pstmt2.setInt(1, i);
			pstmt2.addBatch();
			
		}
		pstmt2.executeBatch();
		pstmt2.close();
		rs.close();
		pstmt.close();
		long ldanach=System.currentTimeMillis();
		
		long dauer=ldanach-lbevor;
		System.out.println("Zeitdauer Kopie 4: "+dauer+"ms");
		
		return dauer;
	}
	
	public void closeConnection() throws SQLException{
		c.close();
	}

}
