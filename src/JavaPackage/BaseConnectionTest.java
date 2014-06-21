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

public class BaseConnectionTest {

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
		UserConnection user = new UserConnection((BasicDataSource) source);
		user.insertIntoUsers("Tedo", "Chubinidze", "tedochubinidze@yahoo.com",
				"123", true, "'1994-02-04'");
		user.CloseConnection();
	}

	public boolean isTrue(double first, double second) {
		return (first == second);
	}

	@Test
	public void selectByIDTest() {
		try {
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
			BaseConnection base = new BaseConnection((BasicDataSource) source);
			rs = base.selectByID("Users", ID, "ID");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(ID, rs.getInt("ID"));
				assertEquals("123", rs.getString("Password"));
				assertEquals("Tedo", rs.getString("FirstName"));
				assertEquals("Chubinidze", rs.getString("LastName"));
				assertEquals(true, rs.getBoolean("Gender"));
				assertEquals(Date.valueOf("1994-02-04"),
						rs.getDate("BirthDate"));
				assertEquals("tedochubinidze@yahoo.com", rs.getString("Email"));
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else {
				assertEquals(1, 2);
			}
			con.close();
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectAllTest() {
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			int ID = 0;
			ResultSet rs = stmt.executeQuery("SELECT * from Users");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			BaseConnection base = new BaseConnection((BasicDataSource) source);
			rs = base.selectAll("Users");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(ID, rs.getInt("ID"));
				assertEquals("123", rs.getString("Password"));
				assertEquals("Tedo", rs.getString("FirstName"));
				assertEquals("Chubinidze", rs.getString("LastName"));
				assertEquals(true, rs.getBoolean("Gender"));
				assertEquals(Date.valueOf("1994-02-04"),
						rs.getDate("BirthDate"));
				assertEquals("tedochubinidze@yahoo.com", rs.getString("Email"));
				assertEquals(false, rs.next());
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else {
				assertEquals(1, 2);
			}
			con.close();
			base.CloseConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		try {
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
			EventConnection event = new EventConnection(
					(BasicDataSource) source);
			event.insertIntoEvents(ID, 1, 0, 4.2, 3.2, 1.2, 2.3, "Tbilisi",
					"Batumi", true, true);
			event.insertIntoEvents(ID, 2, 2.0, 1.1, 1.2, 1.3, 1.4, "terjola",
					"bobokvati", false, false);

			BaseConnection base = new BaseConnection((BasicDataSource) source);
			rs = base.selectEvent(ID, "Events", 1);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(rs.getInt("Places"), 2);
				assertEquals(isTrue(2.0, rs.getDouble("Fee")), true);
				assertEquals(isTrue(1.1, rs.getDouble("FromLongitude")), true);
				assertEquals(isTrue(1.2, rs.getDouble("FromLatitude")), true);
				assertEquals(isTrue(1.3, rs.getDouble("ToLongitude")), true);
				assertEquals(isTrue(1.4, rs.getDouble("ToLatitude")), true);
				assertEquals(rs.getString("FromPlace"), "terjola");
				assertEquals(rs.getString("ToPlace"), "bobokvati");
				assertEquals(rs.getBoolean("Type"), false);
				assertEquals(rs.getBoolean("Validation"), false);
				stmt.executeUpdate("DELETE FROM Events WHERE UserID =" + ID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else {
				assertEquals(1, 2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}