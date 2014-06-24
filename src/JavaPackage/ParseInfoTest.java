package JavaPackage;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;


public class ParseInfoTest {
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
	}

	@Test
	public void TestGetPrivateInfo(){
		int userID = 0;
		Connection con;
		try {
			con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Events(UserID, FromPlace, ToPlace) Values (" + userID
					+ ", 'Tbilisi' , 'Batumi' )");
			
			ParseInfo inf = new ParseInfo((BasicDataSource) source);
			assertEquals("Tbilisi" ,inf.getPrivateInfo(userID, "FromPlace", "Events"));
			assertEquals("Batumi" ,inf.getPrivateInfo(userID, "ToPlace", "Events"));
			stmt.executeUpdate("DELETE FROM Events ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestGetInfoAboutStatuses(){
		int userID = 0;
		Connection con;
		try {
			con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Statuses(UserID, Status) Values (" + userID + ", 'statusee')");
			ParseInfo inf = new ParseInfo((BasicDataSource) source);
			assertEquals(inf.getInfoAboutStatuses(userID), "statusee");
			stmt.executeUpdate("DELETE FROM Statuses ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestGetInfoAboutTel(){
		int userID = 0;
		Connection con;
		try {
			con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			stmt.executeUpdate("INSERT INTO Users (FirstName, LastName, Gender, Password, EMail) Values ('tedo' ,'chubo', true, '123', 'tedo')");
			ResultSet rs = stmt
					.executeQuery("SELECT ID FROM Users Where EMail = 'tedo'");
			if (rs.isBeforeFirst()) {
				rs.next();
				userID = rs.getInt("ID");
			}
			stmt.executeUpdate("INSERT INTO Tel(UserID, PhoneNumber) Values (" + userID + ", '593677827')");
			ParseInfo inf = new ParseInfo((BasicDataSource) source);
			assertEquals(inf.getInfoAboutTel(userID), "593677827");
			stmt.executeUpdate("DELETE FROM Tel ORDER BY ID DESC LIMIT 1");
			stmt.executeUpdate("DELETE FROM Users ORDER BY ID DESC LIMIT 1");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
