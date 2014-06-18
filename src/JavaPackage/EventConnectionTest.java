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

	// testis chatarebamde sachiroa eventebis cxrilshi chaematos 5 minimum
	// shemdegi
	// record,romlis inserti shemdegnairad gamoiyureba:
	// insert into Events
	// (userID,places,fee,FromLongitude,FromLatitude,ToLongitude,ToLatitude,fromPlace,toPlace,type,validation)
	// values(1, 5, 10, 1.1, 2.1, 3.1, 4.1, "tbilisi","batumi", false, true);

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
		base.insertIntoEvents(1, 5, 10, 1.1, 2.1, 3.1, 4.1, "tbilisi",
				"batumi", false, true);
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Events order by ID desc limit 1");
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
				stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
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
			ResultSet rs1 = stmt
					.executeQuery("SELECT * FROM Events order by ID desc limit 1");
			rs1.next();
			int ID = rs1.getInt("ID");
			base.insertIntoDates(ID, "1000-01-01 00:00:00");
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Dates order by ID desc limit 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(ID, rs.getInt("eventID"));
				assertEquals("1000-01-01 00:00:00.0", rs.getString("date"));
				stmt.executeUpdate("DELETE FROM Dates ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertIntoEveryday() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs1 = stmt
					.executeQuery("SELECT * FROM Events order by ID desc limit 1");
			rs1.next();
			int ID = rs1.getInt("ID");
			base.insertIntoEveryday(ID, "00:00:00", 5);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Everyday order by ID desc limit 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(ID, rs.getInt("eventID"));
				assertEquals(5, rs.getInt("day"));
				assertEquals("00:00:00", rs.getString("StartTime"));
				stmt.executeUpdate("DELETE FROM Everyday ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
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
			ResultSet rs1 = stmt
					.executeQuery("SELECT * FROM Events order by ID desc limit 1");
			rs1.next();
			int ID = rs1.getInt("ID");
			base.insertIntoParticipants(ID, 1);
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM participants order by ID desc limit 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(ID, rs.getInt("eventID"));
				assertEquals(1, rs.getInt("userID"));
				stmt.executeUpdate("DELETE FROM Participants ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// am testis chatarebis win sachiroa user-ebshi am insertis damateba
	// mysqldan.
	// insert into users (FirstName, LastName, Gender, BirthDate, Password,
	// EMail)
	// values (N'achi', N'baxlosania', true, '1994-08-23' ,
	// N'40bd001563085fc35165329ea1ff5c5ecbdbbeef',
	// N'achi_baxlosania@yahoo.com');
	@Test
	public void testInsertIntoComments() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs1 = stmt
					.executeQuery("SELECT * FROM Events order by ID desc limit 1");
			rs1.next();
			int ID = rs1.getInt("ID");
			base.insertIntoComments(ID, 1, "sandro lezhava");
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM Comments order by ID desc limit 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(ID, rs.getInt("eventID"));
				assertEquals(1, rs.getInt("userID"));
				assertEquals("sandro lezhava", rs.getString("comment"));
				//assertEquals("1000-01-01 00:00:00.0", rs.getString("date"));
				stmt.executeUpdate("DELETE FROM Comments ORDER BY ID DESC LIMIT 1");
				stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateEvent() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs1 = stmt
					.executeQuery("SELECT * FROM Events order by ID desc limit 1");
			rs1.next();
			int ID = rs1.getInt("ID");
			base.updateEvent(ID, false);
			ResultSet rs = stmt.executeQuery("SELECT * FROM Events where ID ="
					+ ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(false, rs.getBoolean("validation"));
				stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
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
