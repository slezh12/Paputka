package JavaPackage;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
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
		Date date = Date.valueOf("1994-02-04");
		
		boolean type = false;
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			
			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ "user1"
					+ "'"
					+ ","
					+ "'"
					+ "ar vici"
					+ "'"
					+ ","
					+ "'"
					+ "asaaaaaasdpsdasd,mjniaj"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'"
					+ ","
					+ true + "," + "'" + date + "'" + ")");
			int lastID1 = 0;
			ResultSet res1 = stmt.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while(res1.next()){
				 lastID1 = res1.getInt("ID");
				
			}	
			
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO WantToGo (UserID , title, FromLongitude , FromLatitude ,ToLongitude, ToLatitude , Type) VALUES("
					+ lastID1
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
			assertEquals(lastID, temp.getLastID(lastID1));
			assertFalse(isEqual(-1, temp.getLastID(lastID1)));
			
			stmt.executeUpdate("DELETE FROM WantToGo where ID = " + lastID);
			stmt.executeUpdate("DELETE FROM Users where ID = " + lastID1);
		//	stmt.executeUpdate("DELETE FROM WantToGO WHERE UserID  = "
		//				+ 1);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// checks if two ints equals to each other
	private boolean isEqual(double first, double second) {
		return (first == second);
	}
	
	@Test
	public void testgetWantToGos() {
		Date date = Date.valueOf("1994-02-04");
		
		boolean type = false;
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
//			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
//			int userID = 0;
//			int EventID = 0;
//			ResultSet rs = stmt
//					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
//			if (rs.isBeforeFirst()) {
//				rs.next();
//				userID = rs.getInt("ID");
//			}
			
			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ "user1"
					+ "'"
					+ ","
					+ "'"
					+ "ar vici"
					+ "'"
					+ ","
					+ "'"
					+ "mnmnmj"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'"
					+ ","
					+ true + "," + "'" + date + "'" + ")");
			int lastID1 = 0;
			ResultSet res1 = stmt.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while(res1.next()){
				 lastID1 = res1.getInt("ID");
				
			}	
			
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO WantToGo (UserID , title, FromLongitude , FromLatitude ,ToLongitude, ToLatitude , Type) VALUES("
					+ lastID1
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
					+ lastID1
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
			assertEquals(2, temp.getWantToGos(lastID1).size());
			assertFalse(isEqual(3, temp.getLastID(lastID1)));
			
			stmt.executeUpdate("DELETE FROM WantToGo Where UserID = " + lastID1);
			stmt.executeUpdate("DELETE FROM Users where ID = " + lastID1);

		//	stmt.executeUpdate("DELETE FROM WantToGO WHERE UserID  = "
		//				+ 1);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetWantToGos2() {
		Date date = Date.valueOf("1994-02-04");
		boolean type = false;
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
//			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
//			int userID = 0;
//			int EventID = 0;
//			ResultSet rs = stmt
//					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
//			if (rs.isBeforeFirst()) {
//				rs.next();
//				userID = rs.getInt("ID");
//			}
//			stmt.executeQuery("USE " + database);
			
			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ "user1"
					+ "'"
					+ ","
					+ "'"
					+ "ar vici"
					+ "'"
					+ ","
					+ "'"
					+ "asaasdadbasadsfaaaplj"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'"
					+ ","
					+ true + "," + "'" + date + "'" + ")");
			int lastID1 = 0;
			ResultSet res1 = stmt.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while(res1.next()){
				 lastID1 = res1.getInt("ID");
				
			}	
			
			stmt.executeUpdate("INSERT INTO WantToGo (UserID , title, FromLongitude , FromLatitude ,ToLongitude, ToLatitude , Type) VALUES("
					+ lastID1
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
			assertEquals(1, temp.getWantToGos(lastID1).size());
			assertEquals(lastID1, temp.getWantToGos(lastID1).get(0).getUserID());
			assertTrue(temp.getWantToGos(lastID1).get(0).getTitle().equals("bla2"));
			assertFalse(type);
			
			stmt.executeUpdate("DELETE FROM WantToGo where UserID = " + lastID1);
			stmt.executeUpdate("DELETE FROM Users where ID = " + lastID1);
				
		//	stmt.executeUpdate("DELETE FROM WantToGO WHERE UserID  = "
		//				+ 1);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetWantToGo(){
		Date date = Date.valueOf("1994-02-04");

		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
//			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedoo')");
//			int userID = 0;
//			int EventID = 0;
//			ResultSet rs = stmt
//					.executeQuery("SELECT ID FROM Users Where EMail = 'tedoo'");
//			if (rs.isBeforeFirst()) {
//				rs.next();
//				userID = rs.getInt("ID");
//			}
			
			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ "user1"
					+ "'"
					+ ","
					+ "'"
					+ "ar vici"
					+ "'"
					+ ","
					+ "'"
					+ "asaasdaaassdasdaj"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'"
					+ ","
					+ true + "," + "'" + date + "'" + ")");
			int lastID1 = 0;
			ResultSet res1 = stmt.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while(res1.next()){
				 lastID1 = res1.getInt("ID");
				
			}	
			
			
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO WantToGo (UserID , title, FromLongitude , FromLatitude ,ToLongitude, ToLatitude , Type) VALUES("
					+ lastID1
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
			WantToGo t = temp.getWantToGo(lastID, lastID1);
			assertEquals(t.getID(), lastID);
			assertEquals(t.getUserID(), lastID1);
			assertEquals(t.getTitle(), "bla");
			assertEquals(t.getType(), false);
			assertEquals(isEqual(t.getFromLatitude(),0), true);
			assertEquals(isEqual(t.getFromLongitude(),0), true);
			assertEquals(isEqual(t.getToLatitude(),0), true);
			assertEquals(isEqual(t.getToLongitude(),0), true);
			
			stmt.executeUpdate("DELETE FROM WantToGo where UserID = " + lastID1);
			stmt.executeUpdate("DELETE FROM Users where ID = " + lastID1);
		
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
