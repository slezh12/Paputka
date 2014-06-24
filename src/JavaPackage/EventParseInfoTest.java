package JavaPackage;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

public class EventParseInfoTest {

	private DataSource source;
	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	
	
	@Before
	public void setUp(){
		source = new BasicDataSource();
    	((BasicDataSource) source).setDriverClassName("com.mysql.jdbc.Driver");
    	((BasicDataSource) source).setUsername(account);
    	((BasicDataSource) source).setPassword(password);
    	((BasicDataSource) source).setUrl("jdbc:mysql://"+server+":3306/"+database);
	}
	
	
	@Test
	public void testGetEventByID() {
		Date date = Date.valueOf("1994-02-04");
		boolean type = true;
		boolean validation = false;
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
					+ "bbbbbaapouiuaaaaaaaaa"
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
			
			
			stmt
			.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
					+ lastID1
					+ ","
					+ 5
					+ ","
					+ 10
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ type + "," + validation + ")");
			
			int eventsLastID = 0;
			int eventsPlaces = 0;
			int eventsFee = 0;
			String fromplace = "";
			String toPlace = "";
			
			ResultSet resForEvents = stmt.executeQuery("SELECT * FROM EVENTS ORDER BY ID DESC LIMIT 1");
			while(resForEvents.next()){
				eventsLastID = resForEvents.getInt("ID");
				eventsPlaces = resForEvents.getInt("Places");
				eventsFee = resForEvents.getInt("Fee");
				fromplace = resForEvents.getString("FromPlace");
				toPlace = resForEvents.getString("ToPlace");
			}	
			
			
			EventParseInfo info = new EventParseInfo((BasicDataSource) source);
			Event returnedEvent = info.getEventByID(eventsLastID);
			assertEquals(eventsPlaces,returnedEvent.getPlaces());
			assertTrue(eventsFee == returnedEvent.getPrice());
			assertFalse(eventsFee == returnedEvent.getPrice() + 2);
			
			
			stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testGetLastEvents() {
		Date date = Date.valueOf("1994-02-04");
		boolean type = true;
		boolean validation = false;
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
					+ "bbbasdsxljkjkiiadsaaaaaaaaa"
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
			
			
			stmt
			.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
					+ lastID1
					+ ","
					+ 5
					+ ","
					+ 10
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ type + "," + validation + ")");
			
			stmt
			.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
					+ lastID1
					+ ","
					+ 5
					+ ","
					+ 20
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ type + "," + validation + ")");
			
			stmt
			.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
					+ lastID1
					+ ","
					+ 10
					+ ","
					+ 10
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ type + "," + validation + ")");
			
			stmt
			.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
					+ lastID1
					+ ","
					+ 5
					+ ","
					+ 10
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ type + "," + validation + ")");
			
			for(int i = 0; i < 11 ; i++){
				stmt
				.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
						+ lastID1
						+ ","
						+ i
						+ ","
						+ i + 5
						+ ","
						+ 0
						+ ","
						+ 0
						+ ","
						+ 0
						+ ","
						+ 0
						+ ","
						+ "'"
						+ "bla"
						+ "'"
						+ ","
						+ "'"
						+ "bla"
						+ "'"
						+ ","
						+ type + "," + validation + ")");
			}
		
			
			
			EventParseInfo info = new EventParseInfo((BasicDataSource) source);
			assertEquals(info.getLastEvents().size(),15);
			
			
			
			stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 15");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetComments() {
		Date date = Date.valueOf("1994-02-04");
		
		boolean type = true;
		boolean validation = false;
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
					+ "bbasdasadsaaaaaaaaa"
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
			
			
			stmt
			.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
					+ lastID1
					+ ","
					+ 5
					+ ","
					+ 10
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ type + "," + validation + ")");
			
			int eventsLastID = 0;
			ResultSet resForEvents = stmt.executeQuery("SELECT * FROM EVENTS ORDER BY ID DESC LIMIT 1");
			while(resForEvents.next()){
				eventsLastID = resForEvents.getInt("ID");
			}	
			
			
			stmt
			.executeUpdate("INSERT INTO COMMENTS (userID,EventID,Comment) VALUES("
					+ lastID1
					+ ","
					+ eventsLastID
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ")");

			
			EventParseInfo info = new EventParseInfo((BasicDataSource) source);
			assertEquals(info.getComments(eventsLastID).size(),1);
			
			stmt
			.executeUpdate("INSERT INTO COMMENTS (userID,EventID,Comment) VALUES("
					+ lastID1
					+ ","
					+ eventsLastID
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ")");
			
			assertEquals(info.getComments(eventsLastID).size(),2);
			
			stmt.executeUpdate("DELETE FROM Comments ORDER BY ID DESC LIMIT 2");
			stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLastID() {
		Date date = Date.valueOf("1994-02-04");
		
		boolean type = true;
		boolean validation = false;
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
					+ "bbadsasdasaaaaaaaaa"
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
			
			
			stmt
			.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
					+ lastID1
					+ ","
					+ 5
					+ ","
					+ 10
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ type + "," + validation + ")");
			
			int eventsLastID = 0;
			ResultSet resForEvents = stmt.executeQuery("SELECT * FROM EVENTS ORDER BY ID DESC LIMIT 1");
			while(resForEvents.next()){
				eventsLastID = resForEvents.getInt("ID");
			}	
			
			EventParseInfo info = new EventParseInfo((BasicDataSource) source);
			assertEquals(info.getLastID(lastID1),eventsLastID);
			
			stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testgetEventsAndGetUsersEvents() {
		Date date = Date.valueOf("1994-02-04");
		
		boolean type = true;
		boolean validation = false;
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
					+ "bbasdadsaaaaaaaaa"
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
			
			
			for(int i = 0; i < 10 ; i++){
				stmt
				.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
						+ lastID1
						+ ","
						+ i
						+ ","
						+ i + 5
						+ ","
						+ 0
						+ ","
						+ 0
						+ ","
						+ 0
						+ ","
						+ 0
						+ ","
						+ "'"
						+ "bla"
						+ "'"
						+ ","
						+ "'"
						+ "bla"
						+ "'"
						+ ","
						+ type + "," + validation + ")");
			}
		
			
			EventParseInfo info = new EventParseInfo((BasicDataSource) source);
			assertTrue(info.getEvents().size() > 9);
			assertFalse(info.getEvents().size() < 10);
			assertEquals(info.getUsersEvents(lastID1).size(),10);
			
			stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 10");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testInsertIntoComments() {
		Date date = Date.valueOf("1994-02-04");
		
		boolean type = true;
		boolean validation = false;
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
					+ "bbadaaaaa"
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
			
			
			stmt
			.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
					+ lastID1
					+ ","
					+ 5
					+ ","
					+ 10
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ type + "," + validation + ")");
			
			int eventsLastID = 0;
			ResultSet resForEvents = stmt.executeQuery("SELECT * FROM EVENTS ORDER BY ID DESC LIMIT 1");
			while(resForEvents.next()){
				eventsLastID = resForEvents.getInt("ID");
			}	
			
			String comment = "ragac comentari";
			
			EventParseInfo info = new EventParseInfo((BasicDataSource) source);
			info.InsertIntoComments(eventsLastID, lastID1, comment);
			
			int commentLastID = 0;
			ResultSet resComments = stmt.executeQuery("SELECT * FROM Comments ORDER BY ID DESC LIMIT 1");
			while(resComments.next()){
				commentLastID = resComments.getInt("ID");
			}	
			
			int userID = 0;
			int eventID = 0;
			String text  = ""; 
			ResultSet result = stmt
					.executeQuery("SELECT * FROM Comments WHERE ID = " + commentLastID );
			result.next();
			userID  = result.getInt("UserID");
			eventID = result.getInt("EventID");
			text = result.getString("Comment");
			
			assertEquals(userID,lastID1);
			assertEquals(eventID, eventsLastID);
			assertTrue(text.equals(comment));
			assertFalse(text.equals("ragac sxva texti"));
			
			stmt.executeUpdate("DELETE FROM Comments ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testEveryDayDates() {
		Date date = Date.valueOf("1994-02-04");
		
		boolean type = true;
		boolean validation = false;
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
					+ "bbadsaaaaaaa"
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
			
			
			stmt
			.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
					+ lastID1
					+ ","
					+ 5
					+ ","
					+ 10
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ type + "," + validation + ")");
			
			int eventsLastID = 0;
			ResultSet resForEvents = stmt.executeQuery("SELECT * FROM EVENTS ORDER BY ID DESC LIMIT 1");
			while(resForEvents.next()){
				eventsLastID = resForEvents.getInt("ID");
			}	
			
			
			for(int i = 0;  i < 10 ; i++ ){
				stmt
				.executeUpdate("INSERT INTO EVERYDAY (EventID) VALUES("
						+ eventsLastID
						+ ")");
			}
			
			EventParseInfo info = new EventParseInfo((BasicDataSource) source);
			assertTrue(info.EveryDayDates(eventsLastID).size() > 9);
			assertFalse(info.EveryDayDates(eventsLastID).size() < 10);
			
			stmt.executeUpdate("DELETE FROM EveryDay ORDER BY ID DESC LIMIT 10");
			stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testHasRequest() {
		Date date = Date.valueOf("1994-02-04");
		
		boolean type = true;
		boolean validation = false;
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
					+ "asaaaaaasdpklasdasdaj"
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
			
			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ "user2"
					+ "'"
					+ ","
					+ "'"
					+ "ar vici"
					+ "'"
					+ ","
					+ "'"
					+ "waeaaalplkaplkaa"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'"
					+ ","
					+ true + "," + "'" + date + "'" + ")");
			
			int lastID2 = 0;
			ResultSet res2 = stmt.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while(res2.next()){
				 lastID2 = res2.getInt("ID");
				
			}	
			
			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ "user3"
					+ "'"
					+ ","
					+ "'"
					+ "ar vici"
					+ "'"
					+ ","
					+ "'"
					+ "adaaaaaaaaaasdasdg"
					+ "'"
					+ ","
					+ "'"
					+ "password3"
					+ "'"
					+ ","
					+ true + "," + "'" + date + "'" + ")");

			
			int lastID3 = 0;
			ResultSet res3 = stmt.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while(res3.next()){
				 lastID3 = res3.getInt("ID");
				
			}	
			
			stmt
			.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
					+ lastID1
					+ ","
					+ 5
					+ ","
					+ 10
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ 0
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ "'"
					+ "bla"
					+ "'"
					+ ","
					+ type + "," + validation + ")");
			
			int eventsLastID = 0;
			ResultSet resForEvents = stmt.executeQuery("SELECT * FROM EVENTS ORDER BY ID DESC LIMIT 1");
			while(resForEvents.next()){
				eventsLastID = resForEvents.getInt("ID");
			}	
			
	
			stmt.executeUpdate("INSERT INTO Requests(UserID, EventID, Text, Acception, Date) VALUES ("
					+ lastID2
					+ ","
					+ eventsLastID
					+ ","
					+ "'"
					+ "ragac texti"
					+ "'"
					+ ", 0, now())");
			
			EventParseInfo info = new EventParseInfo((BasicDataSource) source);
			assertTrue(info.HasRequest(eventsLastID, lastID2));
			assertFalse(info.HasRequest(eventsLastID, lastID3));
			
			stmt.executeUpdate("DELETE FROM Requests ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 3");
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
