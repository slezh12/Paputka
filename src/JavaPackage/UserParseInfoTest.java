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
	public void setUp() {
		source = new BasicDataSource();
		((BasicDataSource) source).setDriverClassName("com.mysql.jdbc.Driver");
		((BasicDataSource) source).setUsername(account);
		((BasicDataSource) source).setPassword(password);
		((BasicDataSource) source).setUrl("jdbc:mysql://" + server + ":3306/"
				+ database);
	}

	/*
	 * gaketebulia insert into users (FirstName, LastName, Gender, BirthDate,
	 * Password, EMail) values (N'achi', N'baxlosania', true, '1994-08-23' ,
	 * N'40bd001563085fc35165329ea1ff5c5ecbdbbeef',
	 * N'achi_baxlosania@yahoo.com');
	 */
	@Test
	public void testGetUser() {

		UserParseInfo info = new UserParseInfo((BasicDataSource) source);
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("insert into users (FirstName, LastName, Gender, BirthDate, Password, EMail) values ('achi', 'baxlosania', true, '1994-08-23' ,  '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'achi_baxlosania@yahoo.com')");

			User newuser = info.getUser("achi_baxlosania@yahoo.com", "123");
			assertEquals("achi", newuser.getFirstName());
			assertEquals("baxlosania", newuser.getLastName());
			assertEquals(true, newuser.getGender());
			assertEquals("achi_baxlosania@yahoo.com", newuser.getEmail());
			stmt.executeUpdate("DELETE FROM USERS WHERE EMail = 'achi_baxlosania@yahoo.com'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testIsUserAlreadyInBase() {

		// UserParseInfo info = new UserParseInfo((BasicDataSource) source);
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

	@Test
	public void testGetUserByID() {

		// UserParseInfo info = new UserParseInfo((BasicDataSource) source);
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

			ResultSet res = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res.next()) {
				int lastID = res.getInt("ID");
				assertEquals(lastID, info.getUserByID(lastID).getID());
				assertTrue(info.getUserByID(lastID).getFirstName()
						.equals("testi"));
				assertTrue(info.getUserByID(lastID).getLastName()
						.equals("testi"));
				assertTrue(info.getUserByID(lastID).getEmail().equals("testi"));
				assertTrue(info.getUserByID(lastID).getGender());
			}

			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
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
					+ "bbbbbaaaaaaaaaaaaaa"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'"
					+ ","
					+ true
					+ ","
					+ "'"
					+ date
					+ "'"
					+ ")");
			int lastID1 = 0;
			ResultSet res1 = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res1.next()) {
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
					+ "dddddasdfaddddddd"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'" + "," + true + "," + "'" + date + "'" + ")");

			int lastID2 = 0;
			ResultSet res2 = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res2.next()) {
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
					+ "adfasaaaaaaaaaa"
					+ "'"
					+ ","
					+ "'"
					+ "password3"
					+ "'" + "," + true + "," + "'" + date + "'" + ")");

			int lastID3 = 0;
			ResultSet res3 = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res3.next()) {
				lastID3 = res3.getInt("ID");

			}

			stmt.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
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
					+ "'" + "," + type + "," + validation + ")");

			int eventsLastID = 0;
			ResultSet resForEvents = stmt
					.executeQuery("SELECT * FROM EVENTS ORDER BY ID DESC LIMIT 1");
			while (resForEvents.next()) {
				eventsLastID = resForEvents.getInt("ID");
			}

			stmt.executeUpdate("INSERT INTO PARTICIPANTS (UserID,EventID) VALUES("
					+ lastID2 + "," + eventsLastID + ")");

			UserParseInfo info = new UserParseInfo((BasicDataSource) source);

			assertTrue(info.canRate(lastID2, lastID1));
			assertFalse(info.canRate(lastID3, lastID1));
			assertFalse(info.canRate(lastID3, lastID2));
			stmt.executeUpdate("DELETE FROM PARTICIPANTS ORDER BY ID DESC LIMIT 1");

			stmt.executeUpdate("DELETE FROM EVENTS ORDER BY ID DESC LIMIT 1");

			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 3");

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
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
					+ "asaaaaaaasdasdasdaj"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'"
					+ ","
					+ true
					+ ","
					+ "'"
					+ date
					+ "'"
					+ ")");
			int lastID1 = 0;
			ResultSet res1 = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res1.next()) {
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
					+ "waeaaaaaa"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'"
					+ "," + true + "," + "'" + date + "'" + ")");

			int lastID2 = 0;
			ResultSet res2 = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res2.next()) {
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
					+ "adaaaaaaaaasadg"
					+ "'"
					+ ","
					+ "'"
					+ "password3"
					+ "'" + "," + true + "," + "'" + date + "'" + ")");

			int lastID3 = 0;
			ResultSet res3 = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res3.next()) {
				lastID3 = res3.getInt("ID");

			}

			stmt.executeUpdate("INSERT INTO Events (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation) VALUES("
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
					+ "'" + "," + type + "," + validation + ")");

			int eventsLastID = 0;
			ResultSet resForEvents = stmt
					.executeQuery("SELECT * FROM EVENTS ORDER BY ID DESC LIMIT 1");
			while (resForEvents.next()) {
				eventsLastID = resForEvents.getInt("ID");
			}

			stmt.executeUpdate("INSERT INTO Requests(UserID, EventID, Text, Acception, Date) VALUES ("
					+ lastID2
					+ ","
					+ eventsLastID
					+ ","
					+ "'"
					+ "ragac texti"
					+ "'" + ", 0, now())");

			stmt.executeUpdate("INSERT INTO Requests(UserID, EventID, Text, Acception, Date) VALUES ("
					+ lastID3
					+ ","
					+ eventsLastID
					+ ","
					+ "'"
					+ "ragac texti"
					+ "'" + ", 0, now())");

			UserParseInfo info = new UserParseInfo((BasicDataSource) source);

			assertEquals(0, info.getMyRequests(lastID1).size());
			assertEquals(2, info.getOthrRequests(lastID1).size());
			assertEquals(1, info.getMyRequests(lastID2).size());
			assertEquals(1, info.getMyRequests(lastID3).size());
			assertEquals(0, info.getOthrRequests(lastID2).size());
			assertEquals(0, info.getOthrRequests(lastID3).size());
			stmt.executeUpdate("DELETE FROM Requests ORDER BY ID DESC LIMIT 2");

			stmt.executeUpdate("DELETE FROM EVENTS ORDER BY ID DESC LIMIT 1");

			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 3");

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
					+ "aaaaaaaaadasdfads"
					+ "'"
					+ ","
					+ "'"
					+ "password3"
					+ "'"
					+ ","
					+ true
					+ ","
					+ "'"
					+ date
					+ "'"
					+ ")");

			int lastID = 0;
			ResultSet res = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res.next()) {
				lastID = res.getInt("ID");

			}

			UserParseInfo info = new UserParseInfo((BasicDataSource) source);
			String stringForStatus = "bla";
			info.insertIntoStatuses(stringForStatus, lastID);
			int lastIDforStatus = 0;
			ResultSet resForStatus = stmt
					.executeQuery("SELECT * FROM STATUSES ORDER BY ID DESC LIMIT 1");

			while (resForStatus.next()) {
				lastIDforStatus = resForStatus.getInt("ID");

			}

			int userID = 0;
			String userStatus = "";

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM STATUSES WHERE ID = "
							+ lastIDforStatus);
			rs.next();
			userID = rs.getInt("UserID");
			userStatus = rs.getString("Status");

			assertTrue(userStatus.equals("bla"));
			assertFalse(userStatus.equals("shecdmit dawerili texti"));
			assertEquals(lastID, userID);

			stmt.executeUpdate("DELETE FROM Statuses ORDER BY ID DESC LIMIT 3");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testInsertIntoTel() {
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
					+ "addddddddddddds"
					+ "'"
					+ ","
					+ "'"
					+ "password3"
					+ "'"
					+ ","
					+ true
					+ ","
					+ "'"
					+ date
					+ "'"
					+ ")");

			int lastID = 0;
			ResultSet res = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res.next()) {
				lastID = res.getInt("ID");

			}

			UserParseInfo info = new UserParseInfo((BasicDataSource) source);
			String stringForTel = "598-10-10-10";
			info.insertIntoTel(stringForTel, lastID);
			int lastIDForTel = 0;
			ResultSet resForStatus = stmt
					.executeQuery("SELECT * FROM TEL ORDER BY ID DESC LIMIT 1");

			while (resForStatus.next()) {
				lastIDForTel = resForStatus.getInt("ID");

			}

			int userID = 0;
			String userTel = "";

			ResultSet rs = stmt.executeQuery("SELECT * FROM TEL WHERE ID = "
					+ lastIDForTel);
			rs.next();
			userID = rs.getInt("UserID");
			userTel = rs.getString("PhoneNumber");

			assertTrue(userTel.equals("598-10-10-10"));
			assertFalse(userTel.equals("shecdomit dawerili nomeri"));
			assertEquals(lastID, userID);

			stmt.executeUpdate("DELETE FROM TEL ORDER BY ID DESC LIMIT 1");

			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetUsersBySearch() {
		Date date = Date.valueOf("1994-02-04");
		boolean type = true;
		boolean validation = false;
		try {

			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);

			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ "erti da igive"
					+ "'"
					+ ","
					+ "'"
					+ "ar vici"
					+ "'"
					+ ","
					+ "'"
					+ "wwwwwwwwwwwdasdaj"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'"
					+ ","
					+ true
					+ ","
					+ "'"
					+ date
					+ "'"
					+ ")");
			int lastID1 = 0;
			ResultSet res1 = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res1.next()) {
				lastID1 = res1.getInt("ID");

			}

			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ "erti da igive"
					+ "'"
					+ ","
					+ "'"
					+ "ar vici"
					+ "'"
					+ ","
					+ "'"
					+ "aaaaaaaasdasdaaaaaa"
					+ "'"
					+ ","
					+ "'"
					+ "password"
					+ "'"
					+ ","
					+ true
					+ ","
					+ "'"
					+ date
					+ "'"
					+ ")");

			int lastID2 = 0;
			ResultSet res2 = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res2.next()) {
				lastID2 = res2.getInt("ID");

			}

			stmt.executeUpdate("INSERT INTO Users (FirstName , LastName, EMail , Password , Gender, BirthDate) VALUES("
					+ "'"
					+ "erti da igive"
					+ "'"
					+ ","
					+ "'"
					+ "ar vici"
					+ "'"
					+ ","
					+ "'"
					+ "adasaaaaaaaaaaaaa"
					+ "'"
					+ ","
					+ "'"
					+ "password3"
					+ "'"
					+ ","
					+ true
					+ ","
					+ "'"
					+ date
					+ "'"
					+ ")");

			int lastID3 = 0;
			ResultSet res3 = stmt
					.executeQuery("SELECT * FROM Users ORDER BY ID DESC LIMIT 1");
			while (res3.next()) {
				lastID3 = res3.getInt("ID");

			}

			UserParseInfo info = new UserParseInfo((BasicDataSource) source);

			// assertEquals(3,info.getUsersBySearch("erti da igive").size());
			// assertEquals(1,info.getUsersBySearch("beqa").size());
			// assertEquals(0,info.getUsersBySearch("ragac saxeli").size());

			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 3");

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestInsertIntoAvatars() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs;
//			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
//			int ID = 0;
//			
//			ResultSet rs = stmt
//					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
//			if (rs.isBeforeFirst()) {
//				rs.next();
//				ID = rs.getInt("ID");
//			}
			Date date = Date.valueOf("1994-02-04");
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
					+ "asaalkksdasdaj"
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
			UserParseInfo parse = new UserParseInfo((BasicDataSource) source);
			parse.insertIntoAvatars("snimkebi", lastID1);
			rs = stmt
					.executeQuery("SELECT * FROM Avatars Where UserID = " + lastID1);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals("snimkebi", rs.getString("Image"));
				stmt.executeUpdate("DELETE FROM Avatars ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestSelectFromAvatars() {
		Date date = Date.valueOf("1994-02-04");
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs;
//			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
//			int ID = 0;
//			ResultSet rs = stmt
//					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
//			if (rs.isBeforeFirst()) {
//				rs.next();
//				ID = rs.getInt("ID");
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
					+ "asaalkksdasdaj"
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
			stmt.executeUpdate("INSERT INTO Avatars(UserID, Image) Values(" + lastID1
					+ ", 'snimkebi')");
			UserParseInfo parse = new UserParseInfo((BasicDataSource) source);
			String res = parse.selectFromAvatars(lastID1);
			
			
			assertEquals(res, "snimkebi");

			stmt.executeUpdate("DELETE FROM Avatars ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestUpdateAvatars() {
		Date date = Date.valueOf("1994-02-04");
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs;
//			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
//			int ID = 0;
//			ResultSet rs = stmt
//					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
//			if (rs.isBeforeFirst()) {
//				rs.next();
//				ID = rs.getInt("ID");
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
					+ "asaalkksdasdaj"
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
			stmt.executeUpdate("INSERT INTO Avatars(UserID, Image) Values(" + lastID1
					+ ", 'snimkebi')");
			UserParseInfo parse = new UserParseInfo((BasicDataSource) source);
			parse.updateAvatars("surati", lastID1);
			
			rs = stmt
					.executeQuery("SELECT * FROM Avatars Where UserID = " + lastID1);
			if (rs.isBeforeFirst()) {
				rs.next();
				
				assertEquals("surati", rs.getString("Image"));
				stmt.executeUpdate("DELETE FROM Avatars ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
