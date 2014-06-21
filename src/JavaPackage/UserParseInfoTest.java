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

public class UserParseInfoTest {

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
	
	/* gaketebulia
	 * insert into users (FirstName, LastName, Gender, BirthDate, Password, EMail)
values (N'achi', N'baxlosania', true, '1994-08-23' ,  N'40bd001563085fc35165329ea1ff5c5ecbdbbeef', N'achi_baxlosania@yahoo.com');
	 */
//	@Test
	public void testGetUser() {
		
		UserParseInfo info = new UserParseInfo((BasicDataSource) source);
		
		User newuser = info.getUser("achi_baxlosania@yahoo.com", "123");
		assertEquals(Date.valueOf("1994-08-23"),newuser.getBirthdate());
		assertEquals("achi",newuser.getFirstName());
		assertEquals("baxlosania",newuser.getLastName());
		assertEquals(true,newuser.getGender());
		assertEquals("achi_baxlosania@yahoo.com",newuser.getEmail());
	}
	
//	@Test
	public void testIsUserAlreadyInBase() {
		
	//	UserParseInfo info = new UserParseInfo((BasicDataSource) source);
		Date date = Date.valueOf("1994-02-04");
		
		try {
			
				Connection con = source.getConnection();
				Statement stmt = con.createStatement();
				stmt.executeQuery("USE " + database);
				
				stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
						+ "'"
						+ "testi"
						+ "'"
						+ ","
						+ "'"
						+ "testi"
						+ "'"
						+ ","
						+ "'"
						+ "mail"
						+ "'"
						+ ","
						+ "'"
						+ "testi"
						+ "'"
						+ ","
						+ true + "," + "'" + date + "'" + ")");
				
				
				UserParseInfo info = new UserParseInfo((BasicDataSource) source);
				
				assertTrue(info.isUserAlreadyInBase("mail"));
				assertFalse(info.isUserAlreadyInBase("araswori"));
				
				stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
					
				
				
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
//	@Test
	public void testGetUserByID() {
		
	//	UserParseInfo info = new UserParseInfo((BasicDataSource) source);
		Date date = Date.valueOf("1994-02-04");
		String firstName = "testi";
		String lastName = "testi";
		String eMail = "testi";
		String password = "testi";
		boolean gender = true;
		
		try {
			
				Connection con = source.getConnection();
				Statement stmt = con.createStatement();
				stmt.executeQuery("USE " + database);
				
				stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
						+ "'"
						+ firstName
						+ "'"
						+ ","
						+ "'"
						+ lastName
						+ "'"
						+ ","
						+ "'"
						+ eMail
						+ "'"
						+ ","
						+ "'"
						+ password
						+ "'"
						+ ","
						+ gender + "," + "'" + date + "'" + ")");
				
				
				UserParseInfo info = new UserParseInfo((BasicDataSource) source);
				
				ResultSet res = stmt.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
				while(res.next()){
					int lastID = res.getInt("ID");
					assertEquals(lastID,info.getUserByID(lastID).getID());
					assertTrue(info.getUserByID(lastID).getFirstName().equals("testi"));
					assertTrue(info.getUserByID(lastID).getLastName().equals("testi"));
					assertTrue(info.getUserByID(lastID).getEmail().equals("testi"));
					assertTrue(info.getUserByID(lastID).getGender());
				}	
				
				stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
					
				
				
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	

	
//	@Test
	public void testCanRate() {
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
					+ "asdfhasefaasdasdasfvaasdasdaasdj"
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
					+ "waeasdasdfasdasaddfasa"
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
					+ "adfasdfasdadsadasafassdadg"
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
			
	
			stmt
			.executeUpdate("INSERT INTO PARTICIPANTS (UserID,EventID) VALUES("
					+ lastID2
					+ ","
					+ eventsLastID + ")");
			
			
			UserParseInfo info = new UserParseInfo((BasicDataSource) source);
	
			assertTrue(info.canRate(lastID2, lastID1));
			assertFalse(info.canRate(lastID3, lastID1));
			assertFalse(info.canRate(lastID3, lastID2));
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void testGetMyRequestsAndOhterRequests() {
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
					+ "asdfhasefsdasdasfsadsdasdaj"
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
					+ "waeasdasdfaasdxasdxaasxacaasddf"
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
					+ "adfasdfasdadsadasafaaasadg"
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
			
			stmt.executeUpdate("INSERT INTO Requests(UserID, EventID, Text, Acception, Date) VALUES ("
					+ lastID3
					+ ","
					+ eventsLastID
					+ ","
					+ "'"
					+ "ragac texti"
					+ "'"
					+ ", 0, now())");
			
			
			UserParseInfo info = new UserParseInfo((BasicDataSource) source);
	
			assertEquals(0,info.getMyRequests(lastID1).size());
			assertEquals(2,info.getOthrRequests(lastID1).size());
			assertEquals(1,info.getMyRequests(lastID2).size());
			assertEquals(1,info.getMyRequests(lastID3).size());
			assertEquals(0,info.getOthrRequests(lastID2).size());
			assertEquals(0,info.getOthrRequests(lastID3).size());
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	 @Test
	public void testInsertIntoStatuses() {
		 Date date = Date.valueOf("1994-02-04");
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			
			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ "userForStatus"
					+ "'"
					+ ","
					+ "'"
					+ "ar vici"
					+ "'"
					+ ","
					+ "'"
					+ "adfasasdaadsasdsdasdfads"
					+ "'"
					+ ","
					+ "'"
					+ "password3"
					+ "'"
					+ ","
					+ true + "," + "'" + date + "'" + ")");
			

			int lastID = 0;
			ResultSet res = stmt.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while(res.next()){
				 lastID = res.getInt("ID");
				
			}	
			
			
			UserParseInfo info = new UserParseInfo((BasicDataSource) source);
			String stringForStatus = "bla";
			info.insertIntoStatuses(stringForStatus, lastID);
			int lastIDforStatus = 0;
			ResultSet resForStatus = stmt.executeQuery("SELECT * FROM STATUSES ORDER BY ID DESC LIMIT 1");
			
			while(resForStatus.next()){
				lastIDforStatus = resForStatus.getInt("ID");
				
			}	
			
			
			int userID = 0;
			String userStatus = "";
			
			
			
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM STATUSES WHERE ID = " + lastIDforStatus );
			rs.next();
			userID  = rs.getInt("UserID");
			userStatus = rs.getString("Status");
		
			assertTrue(userStatus.equals("bla"));
			assertFalse(userStatus.equals("shecdmit dawerili texti"));
			assertEquals(lastID,userID);
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

}
