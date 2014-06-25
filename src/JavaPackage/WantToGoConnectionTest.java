package JavaPackage;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

public class WantToGoConnectionTest {

	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;

	private DataSource source;
	private WantToGoConnection base;

	@Before
	public void SetUp() {
		source = new BasicDataSource();
		((BasicDataSource) source).setDriverClassName("com.mysql.jdbc.Driver");
		((BasicDataSource) source).setUsername(account);
		((BasicDataSource) source).setPassword(password);
		((BasicDataSource) source).setUrl("jdbc:mysql://" + server + ":3306/"
				+ database);
		base = new WantToGoConnection((BasicDataSource) source);
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
			base.insertIntoWantToGo(ID, "gamikole", 1.1, 2.1, 3.1, 4.1, false);

			rs = stmt.executeQuery("SELECT * FROM WantToGo Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(ID, rs.getInt("userID"));
				double FromLongitude = rs.getDouble("FromLongitude");
				double FromLatitude = rs.getDouble("FromLatitude");
				double ToLongitude = rs.getDouble("ToLongitude");
				double ToLatitude = rs.getDouble("ToLatitude");
				assertEquals(true, areDoublesEqual(1.1, FromLongitude));
				assertEquals(true, areDoublesEqual(2.1, FromLatitude));
				assertEquals(true, areDoublesEqual(3.1, ToLongitude));
				assertEquals(true, areDoublesEqual(4.1, ToLatitude));
				assertEquals("gamikole", rs.getString("Title"));
				assertEquals(false, rs.getBoolean("type"));
				stmt.executeUpdate("DELETE FROM WantToGo where UserID = " + ID);
				stmt.executeUpdate("DELETE FROM USERS where ID = " + ID);
			} else
				assertEquals(1, 2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testInsertIntoWantToGoEveryDay() {
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
			stmt.executeUpdate("INSERT INTO WantToGo(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM WantToGo Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			base.insertIntoWantToGoEveryday(EventID, "00:00:00", "00:01:00", 5);
			rs = stmt.executeQuery("SELECT * FROM WantToGoEveryday Where WantToGoID = "
					+ EventID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(EventID, rs.getInt("WantToGoID"));
				assertEquals(5, rs.getInt("Day"));
				assertEquals("00:00:00", rs.getString("StartDate"));
				stmt.executeUpdate("DELETE FROM WantToGoEveryday Where WantToGoID = "
						+ EventID);
				stmt.executeUpdate("DELETE FROM WantToGo Where ID = " + EventID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else
				assertEquals(1, 2);
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertIntoWantToGoDates() {
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
			stmt.executeUpdate("INSERT INTO WantToGo(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM WantToGo Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			base.insertIntoWantToGoDates(EventID, "2000-01-01 00:00:00","2001-01-01 00:00:00");
			rs = stmt.executeQuery("SELECT * FROM WantToGoDates Where WantToGoID = "
					+ EventID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(EventID, rs.getInt("WantToGoID"));
				assertEquals("2000-01-01 00:00:00.0", rs.getString("StartDate"));
				assertEquals("2001-01-01 00:00:00.0", rs.getString("EndDate"));
				stmt.executeUpdate("DELETE FROM WantToGoDates Where WantToGoID = "
						+ EventID);
				stmt.executeUpdate("DELETE FROM WantToGo Where ID = " + EventID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			} else
				assertEquals(1, 2);
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void DeleteWantToGo(){
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
			stmt.executeUpdate("INSERT INTO WantToGo(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM WantToGo Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			int check =0;
			stmt.executeUpdate("INSERT INTO WantToGoEveryDay(WantToGoID) Values (" + EventID + ")");
			base.deleteWantToGo(EventID, false);
			rs = stmt.executeQuery("SELECT * FROM WantToGoEveryDay Where WantToGoID = " + EventID);
			while(rs.next()) check++;
			assertEquals(check,0);
			rs = stmt.executeQuery("SELECT * FROM WantToGo Where UserID = " + ID);
			while(rs.next()) check++;
			assertEquals(check,0);
			stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void DeleteWantToGo2(){
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
			stmt.executeUpdate("INSERT INTO WantToGo(UserID) Values (" + ID
					+ " )");
			rs = stmt
					.executeQuery("SELECT ID FROM WantToGo Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				EventID = rs.getInt("ID");
			}
			int check = 0;
			stmt.executeUpdate("INSERT INTO WantToGoDates(WantToGoID) Values (" + EventID + ")");
			base.deleteWantToGo(EventID, true);
			rs = stmt.executeQuery("SELECT * FROM WantToGoDates Where WantToGoID = " + EventID);
			while(rs.next()) check++;
			assertEquals(check,0);
			rs = stmt.executeQuery("SELECT * FROM WantToGo Where UserID = " + ID);
			while(rs.next()) check++;
			assertEquals(check,0);
			stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
