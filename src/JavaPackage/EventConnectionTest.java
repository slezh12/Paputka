package JavaPackage;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventConnectionTest {

	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;

	private DataSource source;
	private EventConnection base;


	@Before
	public void SetUp() {
		source = new BasicDataSource();
		((BasicDataSource) source).setDriverClassName("com.mysql.jdbc.Driver");
		((BasicDataSource) source).setUsername(account);
		((BasicDataSource) source).setPassword(password);
		((BasicDataSource) source).setUrl("jdbc:mysql://" + server + ":3306/"
				+ database);
		base = new EventConnection((BasicDataSource) source);
	}

	private boolean areDoublesEqual(double a, double b) {
		if (a == b) {
			return true;
		}
		return false;
	}

	@Test
	public void testInsertIntoEvents() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int ID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			base.insertIntoEvents(ID, 5, 10, 1.1, 2.1, 3.1, 4.1, "tbilisi",
					"batumi", false, true);

			rs = stmt.executeQuery("SELECT * FROM Events Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(ID, rs.getInt("userID"));
				assertEquals(5, rs.getInt("places"));
				double fee = rs.getDouble("fee");
				double FromLongitude = rs.getDouble("FromLongitude");
				double FromLatitude = rs.getDouble("FromLatitude");
				double ToLongitude = rs.getDouble("ToLongitude");
				double ToLatitude = rs.getDouble("ToLatitude");
				assertEquals(true, areDoublesEqual(10, fee));
				assertEquals(true, areDoublesEqual(1.1, FromLongitude));
				assertEquals(true, areDoublesEqual(2.1, FromLatitude));
				assertEquals(true, areDoublesEqual(3.1, ToLongitude));
				assertEquals(true, areDoublesEqual(4.1, ToLatitude));
				assertEquals("tbilisi", rs.getString("fromPlace"));
				assertEquals("batumi", rs.getString("toPlace"));
				assertEquals(false, rs.getBoolean("type"));
				stmt.executeUpdate("DELETE FROM Events where UserID = " + ID);
				stmt.executeUpdate("DELETE FROM USERS where ID = " + ID);
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertIntoDates() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int ID = 0;
			int EventID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM Events Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			base.insertIntoDates(EventID, "1000-01-01 00:00:00");
			rs = stmt.executeQuery("SELECT * FROM Dates Where EventID = "
					+ EventID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(EventID, rs.getInt("eventID"));
				assertEquals("1000-01-01 00:00:00.0", rs.getString("date"));
				stmt.executeUpdate("DELETE FROM Dates Where EventID = "
						+ EventID);
				stmt.executeUpdate("DELETE FROM Events Where ID = " + EventID);
				stmt.executeUpdate("DELETE FROM USERS WHERE ID = " + ID);
			} else
				assertEquals(1, 2);
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertIntoEveryDay() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int ID = 0;
			int EventID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM Events Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			base.insertIntoEveryday(EventID, "00:00:00", 5);
			rs = stmt.executeQuery("SELECT * FROM Everyday Where EventID = "
					+ EventID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(EventID, rs.getInt("eventID"));
				assertEquals(5, rs.getInt("day"));
				assertEquals("00:00:00", rs.getString("StartTime"));
				stmt.executeUpdate("DELETE FROM Everyday Where EventID = "
						+ EventID);
				stmt.executeUpdate("DELETE FROM Events Where ID = " + EventID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else
				assertEquals(1, 2);
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testInsertIntoComments() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int ID = 0;
			int EventID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM Events Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			base.insertIntoComments(EventID, ID, "nagdi testia");
			rs = base.CommentsByID(EventID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(EventID, rs.getInt("eventID"));
				assertEquals(ID, rs.getInt("userID"));
				assertEquals("nagdi testia", rs.getString("comment"));
				stmt.executeUpdate("DELETE FROM Comments Where EventID = "
						+ EventID);
				stmt.executeUpdate("DELETE FROM Events Where ID = " + EventID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else {
				assertEquals(1, 2);
			}
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	@Test
	public void testInsertIntoRequests(){
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int ID = 0;
			int EventID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM Events Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			base.InsertIntoRequsets(ID, EventID, "gamikole zma");
			rs = base.Request(ID, EventID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(EventID, rs.getInt("EventID"));
				assertEquals(ID, rs.getInt("UserID"));
				assertEquals("gamikole zma", rs.getString("Text"));
				assertEquals(0, rs.getInt("Acception"));
				stmt.executeUpdate("DELETE FROM Requests Where EventID = "
						+ EventID);
				stmt.executeUpdate("DELETE FROM Events Where ID = " + EventID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else {
				assertEquals(1, 2);
			}
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectLastEvents() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int ID = 0;
			int EventID = 0;
			int EventID2 = 0;
			int EventID3 = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM Events Where UserID = " + ID + " ORDER BY ID DESC");
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM Events Where UserID = " + ID + " ORDER BY ID DESC");
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID2 = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM Events Where UserID = " + ID + " ORDER BY ID DESC");
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID3 = rs.getInt("ID");
			}
			rs = base.selectLastEvents(2);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(EventID3, rs.getInt("ID"));
				rs.next();
				assertEquals(EventID2, rs.getInt("ID"));
				stmt.executeUpdate("DELETE FROM Events Where UserID = " + ID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else
				assertEquals(1, 2);
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateEvent(){
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int ID = 0;
			int EventID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID, Validation) Values (" + ID
					+ ", true )");
			rs = stmt
					.executeQuery("SELECT ID FROM Events Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			base.updateEvent(EventID, false);
			rs = stmt.executeQuery("SELECT * FROM Events where ID ="
					+ EventID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(false, rs.getBoolean("validation"));
				assertEquals(EventID, rs.getInt("ID"));
				stmt.executeUpdate("DELETE FROM Events Where ID = " + EventID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else
				assertEquals(1, 2);
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertIntoParticipants() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			int ID = 0;
			int EventID = 0;
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM Events Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			base.insertIntoParticipants(EventID, ID);
			rs = stmt
					.executeQuery("SELECT * From Participants Where EventID = "
							+ EventID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(EventID, rs.getInt("eventID"));
				assertEquals(ID, rs.getInt("userID"));
				assertEquals(base.getParticipantsByEventID(EventID), 1);
				stmt.executeUpdate("DELETE FROM Participants Where EventID = "
						+ EventID);
				stmt.executeUpdate("DELETE FROM Events Where ID = " + EventID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else
				assertEquals(1, 2);
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	@After
	public void after() {
		base.CloseConnection();
	}

}
