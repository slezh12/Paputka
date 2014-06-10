package JavaPackage;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
		base.insertIntoEvents(1, 5, 10, 1.1, 2.1, 3.1, 4.1, "tbilisi",
				"batumi", false, true);
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
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Events WHERE ID = 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(1, rs.getInt("userID"));
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
				// assertEquals(true, rs.getBoolean("validation"));
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertIntoDates() {
		base.insertIntoDates(1, "1000-01-01 00:00:00");
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Dates where ID = 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(1, rs.getInt("eventID"));
				assertEquals("1000-01-01 00:00:00.0", rs.getString("date"));
				stmt.executeUpdate("truncate dates");
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertIntoEveryday() {
		base.insertIntoEveryday(1, "2012-10-03 00:00:00", 5);
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Everyday where ID = 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(1, rs.getInt("eventID"));
				assertEquals(5, rs.getInt("day"));
				// System.out.print(rs.getString("StartTime"));
				// TIME-MA RA UNDA SHEINAXOS??????????????
				// assertEquals("2012-10-03 00:00:00",
				// rs.getString("StartTime"));
				stmt.executeUpdate("truncate Everyday");
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertIntoParticipants() {
		base.insertIntoParticipants(1, 1);
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Participants where ID = 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(1, rs.getInt("eventID"));
				assertEquals(1, rs.getInt("userID"));
				stmt.executeUpdate("truncate participants");
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertIntoComments() {
		base.insertIntoComments(1, 1, "sandro lezhava", "1000-01-01 00:00:00");
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Comments where ID = 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(1, rs.getInt("eventID"));
				assertEquals(1, rs.getInt("userID"));
				assertEquals("sandro lezhava", rs.getString("comment"));
				assertEquals("1000-01-01 00:00:00.0", rs.getString("date"));
				stmt.executeUpdate("truncate comments");
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateEvent() {
		base.updateEvent(1, false);
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Events where ID = 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(false, rs.getBoolean("validation"));
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@After
	public void after() {
		Connection con;
		try {
			con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			//stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		base.CloseConnection();
	}
	
	
}

