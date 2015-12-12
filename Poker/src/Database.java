import java.sql.*;

public class Database {

	private Connection c = null;

	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	public void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS poker" + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "UST LONG NOT NULL," + "High_Card INT NOT NULL," + "Pair INT NOT NULL," + "Two_Pairs INT NOT NULL,"
				+ "Triple INT NOT NULL," + "Straight INT NOT NULL," + "Flush INT NOT NULL," + "Full_House INT NOT NULL,"
				+ "Poker INT NOT NULL," + "Straight_Flush INT NOT NULL," + "Royal_Flush INT NOT NULL);";
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertData(int[] data) {
		long unixTime = System.currentTimeMillis() / 1000L;
		String sql = "INSERT INTO poker(UST,High_Card,Pair,Two_Pairs,Triple,Straight,Flush,Full_House,Poker,Straight_Flush,Royal_Flush) VALUES"
				+ "(" + unixTime + "," + data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + ","
				+ data[5] + "," + data[6] + "," + data[7] + "," + data[8] + "," + data[9] + ");";

		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
