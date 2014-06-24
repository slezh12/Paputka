package JavaPackage;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hamcrest.core.IsEqual;
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
	public void testGetLastID() {
		
		
		boolean type = false;
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int userID = 0;
			int EventID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
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
			assertEquals(lastID, temp.getLastID(userID));
			assertFalse(isEqual(-1, temp.getLastID(userID)));
			
			stmt.executeUpdate("DELETE FROM WantToGo where UserID = " + userID);
			stmt.executeUpdate("DELETE FROM Users where ID = " + userID);
		//	stmt.executeUpdate("DELETE FROM WantToGO WHERE UserID  = "
		//				+ 1);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// checks if two ints equla to each other
	private boolean isEqual(double first, double second) {
		return (first == second);
	}
	
	@Test
	public void testgetWantToGos() {
		
		
		boolean type = false;
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int userID = 0;
			int EventID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
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
			assertEquals(2, temp.getWantToGos(userID).size());
			assertFalse(isEqual(3, temp.getLastID(userID)));
			
			stmt.executeUpdate("DELETE FROM WantToGo Where UserID = " + userID);
			stmt.executeUpdate("DELETE FROM Users where ID = " + userID);

		//	stmt.executeUpdate("DELETE FROM WantToGO WHERE UserID  = "
		//				+ 1);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetWantToGos2() {
		
		boolean type = false;
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int userID = 0;
			int EventID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
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
			assertEquals(1, temp.getWantToGos(userID).size());
			assertEquals(userID, temp.getWantToGos(userID).get(0).getUserID());
			assertTrue(temp.getWantToGos(userID).get(0).getTitle().equals("bla2"));
			assertFalse(type);
			
			stmt.executeUpdate("DELETE FROM WantToGo where UserID = " + userID);
			stmt.executeUpdate("DELETE FROM Users where ID = " + userID);
				
		//	stmt.executeUpdate("DELETE FROM WantToGO WHERE UserID  = "
		//				+ 1);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetWantToGo(){
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int userID = 0;
			int EventID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
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
					+ false +")");
			int lastID = 0;
			ResultSet res = stmt.executeQuery("SELECT * FROM WantToGo ORDER BY ID DESC LIMIT 1");
			while(res.next()){
				 lastID = res.getInt("ID");
				
			}	
			WantToGoParseInfo temp = new WantToGoParseInfo((BasicDataSource) source);
			WantToGo t = temp.getWantToGo(lastID, userID);
			assertEquals(t.getID(), lastID);
			assertEquals(t.getUserID(), userID);
			assertEquals(t.getTitle(), "bla");
			assertEquals(t.getType(), false);
			assertEquals(isEqual(t.getFromLatitude(),0), true);
			assertEquals(isEqual(t.getFromLongitude(),0), true);
			assertEquals(isEqual(t.getToLatitude(),0), true);
			assertEquals(isEqual(t.getToLongitude(),0), true);
			
			stmt.executeUpdate("DELETE FROM WantToGo where UserID = " + userID);
			stmt.executeUpdate("DELETE FROM Users where ID = " + userID);
		
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
