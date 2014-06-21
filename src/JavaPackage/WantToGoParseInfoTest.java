package JavaPackage;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

public class WantToGoParseInfoTest {

	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;

	private DataSource source;

	
	@Before
	public void SetUp() {
		source = new BasicDataSource();
		((BasicDataSource) source).setDriverClassName("com.mysql.jdbc.Driver");
		((BasicDataSource) source).setUsername(account);
		((BasicDataSource) source).setPassword(password);
		((BasicDataSource) source).setUrl("jdbc:mysql://" + server + ":3306/"
				+ database);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetLastID() {
		int userID = 1;
		
		boolean type = false;
		try {
		//	WantToGoConnection base = new WantToGoConnection((BasicDataSource) source);
		//	base.insertIntoWantToGo(userID, "bla", 0, 0, 0, 0, true);
		//	base.CloseConnection();
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO WantToGo (UserID , title, FromLongitude , FromLatitude ,ToLongitude, ToLatitude , Type) VALUES("
					+ userID
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ type +")");
			
			int lastID = 0;
			ResultSet res = stmt.executeQuery("SELECT * FROM WantToGo ORDER BY ID DESC LIMIT 1");
			while(res.next()){
				 lastID = res.getInt("ID");
				
			}	
			WantToGoParseInfo temp = new WantToGoParseInfo((BasicDataSource) source);
			assertEquals(lastID, temp.getLastID(1));
			assertFalse(isEqual(-1, temp.getLastID(1)));
			
			stmt.executeUpdate("DELETE FROM WantToGO ORDER BY ID DESC LIMIT 1");
				
		//	stmt.executeUpdate("DELETE FROM WantToGO WHERE UserID  = "
		//				+ 1);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// checks if two ints equla to each other
	private boolean isEqual(int first, int second) {
		return (first == second);
	}
	
	@Test
	public void testgetWantToGos() {
		int userID = 1;
		
		boolean type = false;
		try {
		//	WantToGoConnection base = new WantToGoConnection((BasicDataSource) source);
		//	base.insertIntoWantToGo(userID, "bla", 0, 0, 0, 0, true);
		//	base.CloseConnection();
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO WantToGo (UserID , title, FromLongitude , FromLatitude ,ToLongitude, ToLatitude , Type) VALUES("
					+ userID
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ type +")");
			
			stmt.executeUpdate("INSERT INTO WantToGo (UserID , title, FromLongitude , FromLatitude ,ToLongitude, ToLatitude , Type) VALUES("
					+ userID
					+ ","
					+ "'"
					+ "bla2"
					+ "'"
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ type +")");
			
			
			WantToGoParseInfo temp = new WantToGoParseInfo((BasicDataSource) source);
			assertEquals(2, temp.getWantToGos(1).size());
			assertFalse(isEqual(3, temp.getLastID(1)));
			
			stmt.executeUpdate("DELETE FROM WantToGO ORDER BY ID DESC LIMIT 2");
				
		//	stmt.executeUpdate("DELETE FROM WantToGO WHERE UserID  = "
		//				+ 1);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetWantToGos2() {
		int userID = 1;
		
		boolean type = false;
		try {
		//	WantToGoConnection base = new WantToGoConnection((BasicDataSource) source);
		//	base.insertIntoWantToGo(userID, "bla", 0, 0, 0, 0, true);
		//	base.CloseConnection();
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			
			
			stmt.executeUpdate("INSERT INTO WantToGo (UserID , title, FromLongitude , FromLatitude ,ToLongitude, ToLatitude , Type) VALUES("
					+ userID
					+ ","
					+ "'"
					+ "bla2"
					+ "'"
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ type +")");
			
			
			WantToGoParseInfo temp = new WantToGoParseInfo((BasicDataSource) source);
			assertEquals(1, temp.getWantToGos(1).size());
			assertEquals(1, temp.getWantToGos(1).get(0).getUserID());
			assertTrue(temp.getWantToGos(1).get(0).getTitle().equals("bla2"));
			assertFalse(type);
			assertFalse(isEqual(4, temp.getLastID(1)));
			
			stmt.executeUpdate("DELETE FROM WantToGO ORDER BY ID DESC LIMIT 1");
				
		//	stmt.executeUpdate("DELETE FROM WantToGO WHERE UserID  = "
		//				+ 1);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
