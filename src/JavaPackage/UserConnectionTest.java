package JavaPackage;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

public class UserConnectionTest {

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
		UserConnection base = new UserConnection((BasicDataSource) source);
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("insert into users (FirstName, LastName, Gender, BirthDate, Password, EMail) values ('achi', 'baxlosania', true, '1994-08-23' ,  '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'achi_baxlosania@yahoo.com')");

			ResultSet rs = base.getInfoByMail("achi_baxlosania@yahoo.com");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals("40bd001563085fc35165329ea1ff5c5ecbdbbeef",
						rs.getString("Password"));
				assertEquals("achi", rs.getString("FirstName"));
				assertEquals("baxlosania", rs.getString("LastName"));
				assertEquals(true, rs.getBoolean("Gender"));
				stmt.executeUpdate("DELETE FROM USERS WHERE EMail = 'achi_baxlosania@yahoo.com'");
			} else {
				assertEquals(1, 2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testInsertIntoUsers() {
		UserConnection base = new UserConnection((BasicDataSource) source);
		String password1 = Hash.calculateHashCode("123456");
		Date date = Date.valueOf("1994-02-04");
		base.insertIntoUsers("Beqa", "Khaburdzania", "bkhab12@freeuni.edu.ge",
				password1, true, "1994-02-04");
		base.CloseConnection();
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Users WHERE EMail = 'bkhab12@freeuni.edu.ge'");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(password1, rs.getString("Password"));
				assertEquals("Beqa", rs.getString("FirstName"));
				assertEquals("Khaburdzania", rs.getString("LastName"));
				assertEquals(true, rs.getBoolean("Gender"));
				assertEquals(date, rs.getDate("BirthDate"));
				stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
				// stmt.executeUpdate("DELETE FROM Users WHERE EMail = 'bkhab12@freeuni.edu.ge'");
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testInsertIntoRequest() {
		UserConnection base = new UserConnection((BasicDataSource) source);
		Date date = Date.valueOf("2000-03-04");
		String textForTest = "ragac texti";
		String textForTest1 = "shemowmeba";
		try {
			int userID = 0;
			int EventID = 0;
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + userID
					+ " )");
			rs = stmt.executeQuery("SELECT ID FROM Events Where UserID = "
					+ userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			base.insertIntoRequestss(userID, EventID, "shemowmeba", 0,
					" '2000-03-04'");
			base.CloseConnection();

			rs = stmt.executeQuery("SELECT * FROM Requests WHERE UserID = "
					+ userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertFalse(textForTest.equals(rs.getString("Text")));
				assertEquals(textForTest1, rs.getString("Text"));

				assertEquals(userID, rs.getInt("UserID"));
				assertFalse(isEqual(-1, rs.getInt("UserID")));

				assertEquals(EventID, rs.getInt("EventID"));
				assertFalse(isEqual(-1, rs.getInt("EventID")));

				assertEquals(0, rs.getInt("Acception"));
				assertFalse(isEqual(-1, rs.getInt("Acception")));

				assertEquals(date, rs.getDate("Date"));

				stmt.executeUpdate("DELETE FROM Requests ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
				// stmt.executeUpdate("DELETE FROM Requests WHERE UserID  = "
				// + userID);
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// checks if two ints equla to each other
	private boolean isEqual(int first, int second) {
		return (first == second);
	}

	// es ar imushavebs anu shecdomaze gava tu baza carielia
	@Test
	public void testUpdateRequest() {
		UserConnection base = new UserConnection((BasicDataSource) source);
		try {
			int userID = 0;
			int EventID = 0;
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + userID
					+ " )");
			rs = stmt.executeQuery("SELECT ID FROM Events Where UserID = "
					+ userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			int requestID = 0;
			stmt.executeUpdate("INSERT INTO requests(UserID, EventID, Acception) Values ( "
					+ userID + ", " + EventID + ", " + 0 + ")");
			rs = stmt.executeQuery("SELECT ID FROM Requests Where UserID = "
					+ userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				requestID = rs.getInt("ID");
			}
			base.updateRequests(requestID, 1);
			rs = stmt.executeQuery("SELECT * FROM Requests WHERE ID = "
					+ requestID);
			if (rs.isBeforeFirst()) {
				rs.next();

				assertEquals(userID, rs.getInt("UserID"));
				assertFalse(isEqual(-1, rs.getInt("UserID")));

				assertEquals(EventID, rs.getInt("EventID"));
				assertFalse(isEqual(-1, rs.getInt("EventID")));

				assertEquals(1, rs.getInt("Acception"));
				assertFalse(isEqual(0, rs.getInt("Acception")));
				stmt.executeUpdate("DELETE FROM Requests ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");

			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// es ar imushavebs anu shecdomaze gava tu baza carielia
	@Test
	public void testUpdate() {
		UserConnection base = new UserConnection((BasicDataSource) source);

		try {
			int userID = 0;
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
			int AvatarID = 1;
			String avatarTextFalse = "Ragac surati";
			stmt.executeUpdate("Insert Into Avatars(UserID, Image) values("
					+ userID + ", 'surati' )");
			rs = stmt.executeQuery("SELECT ID FROM Avatars Where UserID = "
					+ userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				AvatarID = rs.getInt("ID");
			}

			base.update("Avatars", "Image", "sxva surati", userID);
			base.CloseConnection();

			rs = stmt.executeQuery("SELECT * FROM Avatars WHERE ID = "
					+ AvatarID);
			if (rs.isBeforeFirst()) {
				rs.next();

				assertEquals(userID, rs.getInt("UserID"));
				assertFalse(isEqual(0, rs.getInt("UserID")));

				assertFalse(avatarTextFalse.equals(rs.getString("Image")));
				assertEquals("sxva surati", rs.getString("Image"));

				stmt.executeUpdate("DELETE FROM Avatars ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testInsert() {
		UserConnection base = new UserConnection((BasicDataSource) source);

		String imageTrue = "she yvelaze lamazo";
		String imageFalse = "shen vize ras ambob she vapshe yvelaze  lamazo";

		try {
			int userID = 0;

			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
			base.insert("Avatars", "Image", "she yvelaze lamazo", userID);
			rs = stmt.executeQuery("SELECT * FROM Avatars WHERE UserID = "
					+ userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertFalse(imageFalse.equals(rs.getString("Image")));
				assertEquals(imageTrue, rs.getString("Image"));

				assertEquals(userID, rs.getInt("UserID"));
				assertFalse(isEqual(-1, rs.getInt("UserID")));

				stmt.executeUpdate("DELETE FROM Avatars ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
				// stmt.executeUpdate("DELETE FROM Avatars WHERE UserID  = " +
				// 1);
				base.CloseConnection();
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetRating() {
		try {
			UserConnection user = new UserConnection((BasicDataSource) source);
			user.insertIntoUsers("Tedo", "Chubinidze",
					"tedochubinidze@yahoo.com", "123", true, "1994-02-04");
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			int ID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedochubinidze@yahoo.com'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			user.insertIntoUsers("leo", "uduria", "udiria@yahoo.com", "123",
					true, "1992-02-04");
			int ID2 = 0;
			rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'udiria@yahoo.com'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID2 = rs.getInt("ID");
			}
			stmt.executeUpdate("Insert Into Ratings(FirstID, SecondID, Rating) Values("
					+ ID + "," + ID2 + "," + 4 + " )");

			rs = user.getRating(ID, ID2);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(rs.getInt("FirstID"), ID);
				assertEquals(rs.getInt("secondID"), ID2);
				assertEquals(rs.getInt("Rating"), 4);
				stmt.executeUpdate("DELETE FROM RATINGS WHERE FirstID = " + ID);
				stmt.executeUpdate("DELETE FROM Users WHERE ID =" + ID);
				stmt.executeUpdate("DELETE FROM Users WHERE ID =" + ID2);
				user.CloseConnection();
			} else {
				assertEquals(1, 2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void test3() {
		try {
			UserConnection user = new UserConnection((BasicDataSource) source);
			user.insertIntoUsers("Tedo", "Chubinidze",
					"tedochubinidze@yahoo.com", "123", true, "1994-02-04");
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			int ID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedochubinidze@yahoo.com'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			user.insertIntoUsers("leo", "uduria", "udiria@yahoo.com", "123",
					true, "1992-02-04");
			int ID2 = 0;
			rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'udiria@yahoo.com'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID2 = rs.getInt("ID");
			}
			user.insertIntoRatings(ID, ID2, 5);
			user.CloseConnection();
			rs = stmt.executeQuery("SELECT * FROM Ratings Where FirstID = "
					+ ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(rs.getInt("FirstID"), ID);
				assertEquals(rs.getInt("secondID"), ID2);
				assertEquals(rs.getInt("Rating"), 5);
				stmt.executeUpdate("DELETE FROM RATINGS WHERE FirstID = " + ID);
				stmt.executeUpdate("DELETE FROM Users WHERE ID =" + ID);
				stmt.executeUpdate("DELETE FROM Users WHERE ID =" + ID2);
			} else {
				assertEquals(1, 2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void getRaitingBooleanTest() {
		try {
			UserConnection user = new UserConnection((BasicDataSource) source);
			int userID = 0;
			int userID2 = 0;
			int EventID = 0;
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedono' ,'chubi', true, '123', 'tedono')");
			rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedono'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID2 = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + userID
					+ " )");
			rs = stmt.executeQuery("SELECT ID FROM Events Where UserID = "
					+ userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Participants(UserID, EventID) Values ("
					+ userID + "," + EventID + " )");
			stmt.executeUpdate("INSERT INTO Participants(UserID, EventID) Values ("
					+ userID2 + "," + EventID + " )");

			stmt.executeQuery("USE " + database);
			rs = stmt
					.executeQuery("select Participants.ID from participants join events on participants.eventid=events.id  where Participants.userID = 1 and events.UserID = 2");
			ResultSet rs1 = user.getRaitingBoolean(userID, userID2);
			assertEquals(rs.next(), rs1.next());
			stmt.executeUpdate("DELETE FROM Participants ORDER BY ID DESC LIMIT 2");
			stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 2");
			user.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testgetUsersByname() {
		UserConnection user = new UserConnection((BasicDataSource) source);
		try {

			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);

			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('ted' ,'chub', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");

			rs = user.getUsersByName("ted", "chub");
			int check = 0;
			while (rs.next()) {
				if (rs.getString("FirstName").equals("ted")
						&& rs.getString("LastName").equals("chub"))
					check++;
			}
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			assertEquals((check > 0), true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testgetUsersByname2() {
		UserConnection user = new UserConnection((BasicDataSource) source);
		try {

			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);

			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('ted' ,'chub', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");

			rs = user.getUsersByName("ted", "ted");
			int check = 0;
			while (rs.next()) {
				if (rs.getString("FirstName").equals("ted")
						&& rs.getString("LastName").equals("chub"))
					check++;
			}
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			assertEquals((check > 0), true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testgetUsersByname3() {
		UserConnection user = new UserConnection((BasicDataSource) source);
		try {

			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);

			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('ted' ,'chub', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			rs = user.getUsersByName("chub", "chub");
			int check = 0;
			while (rs.next()) {
				if (rs.getString("FirstName").equals("ted")
						&& rs.getString("LastName").equals("chub"))
					check++;
			}
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			assertEquals((check > 0), true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void TestGetUsersRequests() {
		try {
			UserConnection user = new UserConnection((BasicDataSource) source);
			int userID = 0;
			int userID2 = 0;
			int EventID = 0;
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedono' ,'chubi', true, '123', 'tedono')");
			rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedono'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID2 = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + userID
					+ " )");
			rs = stmt.executeQuery("SELECT ID FROM Events Where UserID = "
					+ userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT into Requests(UserID, EventID, Text, Acception) Values ("
					+ userID
					+ ", "
					+ EventID
					+ ", "
					+ "'gamikole zm'"
					+ ","
					+ 0 + ")");
			rs = user.getUsersRequests(userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(EventID, rs.getInt("EventID"));
				assertEquals(userID, rs.getInt("UserID"));
				assertEquals("gamikole zm", rs.getString("Text"));
				assertEquals(0, rs.getInt("Acception"));
				stmt.executeUpdate("DELETE FROM Requests ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 2");
				user.CloseConnection();
			} else {
				assertEquals(1, 2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
