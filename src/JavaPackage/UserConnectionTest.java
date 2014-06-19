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

	/*
	 * gaketebulia insert into users (FirstName, LastName, Gender, BirthDate,
	 * Password, EMail) values (N'achi', N'baxlosania', true, '1994-08-23' ,
	 * N'40bd001563085fc35165329ea1ff5c5ecbdbbeef',
	 * N'achi_baxlosania@yahoo.com');
	 */
	@Test
	public void test() {
		UserConnection base = new UserConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.getInfoByMail("achi_baxlosania@yahoo.com");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals("40bd001563085fc35165329ea1ff5c5ecbdbbeef",
						rs.getString("Password"));
				assertEquals("achi", rs.getString("FirstName"));
				assertEquals("baxlosania", rs.getString("LastName"));
				assertEquals(true, rs.getBoolean("Gender"));
				assertEquals(Date.valueOf("1994-08-23"),
						rs.getDate("BirthDate"));
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
				password1, true, "'1994-02-04'");
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
			//	stmt.executeUpdate("DELETE FROM Users WHERE EMail = 'bkhab12@freeuni.edu.ge'");
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// chems bazashi gavakete ragac cvlilebebi rac damchirda testistvis
	@Test
	public void testInsertIntoRequest() {
		UserConnection base = new UserConnection((BasicDataSource) source);
		Date date = Date.valueOf("2000-03-04");
		String textForTest = "ragac texti";
		String textForTest1 = "shemowmeba";
		int userID = 1;
		base.insertIntoRequestss(userID, 1, "shemowmeba", 0, " '2000-03-04'");
		base.CloseConnection();
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Requests WHERE UserID = "
							+ userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertFalse(textForTest.equals(rs.getString("Text")));
				assertEquals(textForTest1, rs.getString("Text"));

				assertEquals(1, rs.getInt("UserID"));
				assertFalse(isEqual(2, rs.getInt("UserID")));

				assertEquals(1, rs.getInt("EventID"));
				assertFalse(isEqual(2, rs.getInt("EventID")));

				assertEquals(0, rs.getInt("Acception"));
				assertFalse(isEqual(2, rs.getInt("Acception")));

				assertEquals(date, rs.getDate("Date"));
				
				stmt.executeUpdate("DELETE FROM Requests ORDER BY ID DESC LIMIT 1");
		//		stmt.executeUpdate("DELETE FROM Requests WHERE UserID  = "
		//				+ userID);
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

	// am metodis dasatestadac damchirda bazis cvlileba
	@Test
	public void testSelectRatings() {
		int secondID = 1;
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Ratings WHERE SecondID = "
							+ secondID);
			if (rs.isBeforeFirst()) {
				rs.next();

				assertEquals(1, rs.getInt("FirstID"));
				assertFalse(isEqual(2, rs.getInt("FirstID")));

				assertEquals(1, rs.getInt("SecondID"));
				assertFalse(isEqual(2, rs.getInt("SecondID")));

				assertEquals(5, rs.getInt("Rating"));
				assertFalse(isEqual(2, rs.getInt("Rating")));

			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// es ar imushavebs anu shecdomaze gava tu baza carielia
	@Test
	public void testUpdateRequest() {
		UserConnection base = new UserConnection((BasicDataSource) source);
		int requestID = 1;
		base.updateRequestss(requestID, 1);
		base.CloseConnection();
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Requests WHERE ID = "
							+ requestID);
			if (rs.isBeforeFirst()) {
				rs.next();

				assertEquals(1, rs.getInt("UserID"));
				assertFalse(isEqual(2, rs.getInt("UserID")));

				assertEquals(1, rs.getInt("EventID"));
				assertFalse(isEqual(2, rs.getInt("EventID")));

				assertEquals(1, rs.getInt("Acception"));
				assertFalse(isEqual(0, rs.getInt("Acception")));

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
		int AvatarID = 1;
		String avatarTextTrue = "Ragac sxva surati";
		String avatarTextFalse = "Ragac surati";
		base.update("Avatars", "Image", "Ragac sxva surati", AvatarID);
		base.CloseConnection();
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Avatars WHERE ID = "
							+ AvatarID);
			if (rs.isBeforeFirst()) {
				rs.next();

				assertEquals(1, rs.getInt("UserID"));
				assertFalse(isEqual(2, rs.getInt("UserID")));

				assertFalse(avatarTextFalse.equals(rs.getString("Image")));
				assertEquals(avatarTextTrue, rs.getString("Image"));

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

		int userID = 1;
		base.insert("Avatars", "Image", "she yvelaze lamazo", userID);

		base.CloseConnection();
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Avatars WHERE UserID = "
							+ userID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertFalse(imageFalse.equals(rs.getString("Image")));
				assertEquals(imageTrue, rs.getString("Image"));

				assertEquals(1, rs.getInt("UserID"));
				assertFalse(isEqual(2, rs.getInt("UserID")));
				
				stmt.executeUpdate("DELETE FROM Avatars ORDER BY ID DESC LIMIT 1");
		//		stmt.executeUpdate("DELETE FROM Avatars WHERE UserID  = " + 1);
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test3() {
		try {
			UserConnection user = new UserConnection((BasicDataSource) source);
			user.insertIntoUsers("Tedo", "Chubinidze",
					"tedochubinidze@yahoo.com", "123", true, "'1994-02-04'");
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
					true, "'1992-02-04'");
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
	public void getUsersRequestsTest() {
		try {
			UserConnection user = new UserConnection((BasicDataSource) source);
			user.insertIntoUsers("Tedo", "Chubinidze",
					"tedochubinidze@yahoo.com", "123", true, "'1994-02-04'");
			user.CloseConnection();
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
			int EventID = 0;
			EventConnection event = new EventConnection(
					(BasicDataSource) source);
			event.insertIntoEvents(ID, 1, 0, 4.2, 3.2, 1.2, 2.3, "Tbilisi",
					"Batumi", true, true);
			rs = stmt
					.executeQuery("SELECT ID FROM Events Where FromPlace = 'Tbilisi'");
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			event.CloseConnection();
			stmt.executeQuery("INSERT INTO Requests (UserID , EventID, Text , Acception ,Date) VALUES("
					+ ID
					+ ","
					+ EventID
					+ ",'quteisamde gamwie brat', 0,'2014-01-02 00:00:00')");

			UserConnection base = new UserConnection((BasicDataSource) source);
			rs = base.getUsersRequests(ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(EventID, rs.getInt("EventID"));
				assertEquals(ID, rs.getInt("UserID"));
				assertEquals(0, rs.getInt("Acception"));
				assertEquals("quteisamde gamwie brat",rs.getString("Text"));
				assertEquals(Timestamp.valueOf("2014-01-02 00:00:00"),rs.getTimestamp("Date"));
				stmt.executeUpdate("DELETE FROM Events WHERE UserID =" + ID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
				stmt.executeUpdate("DELETE FROM Requests Where UserID = " + ID);
			} else {
				assertEquals(1, 2);
			}
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
