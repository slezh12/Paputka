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
	
	@Before
	public void SetUp(){
		source = new BasicDataSource();
    	((BasicDataSource) source).setDriverClassName("com.mysql.jdbc.Driver");
    	((BasicDataSource) source).setUsername(account);
    	((BasicDataSource) source).setPassword(password);
    	((BasicDataSource) source).setUrl("jdbc:mysql://"+server+":3306/"+database);
	}
	
	
	public boolean isTrue(double first, double second){
		return (first==second);
	}
	
	//@Test
	public void test() {
		try{
			
			UserConnection user = new UserConnection((BasicDataSource) source);
			user.insertIntoUsers("Tedo", "Chubinidze", "tedochubinidze@yahoo.com", "123", true, "'1994-02-04'");
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			int ID = 0;
			ResultSet rs = stmt.executeQuery("SELECT ID FROM Users Where EMail = 'tedochubinidze@yahoo.com'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			WantToGoConnection want = new WantToGoConnection((BasicDataSource) source);
			want.insertIntoRequestss(ID, "casvla mindaa", 1.4, 4.5, 1.3, 4.2, true);
			want.CloseConnection();
			user.CloseConnection();
			rs = stmt.executeQuery("SELECT * FROM WantToGo where userID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(rs.getString("title"), "casvla mindaa");
				double k = 1.4;
				double p = rs.getDouble("FromLongitude");
				assertEquals(isTrue(k,p), true);
				k = 4.5;
				p = rs.getDouble("FromLatitude");
				assertEquals(isTrue(k,p), true);
				k = 1.3;
				p = rs.getDouble("ToLongitude");
				assertEquals(isTrue(k,p), true);
				k = 4.2;
				p = rs.getDouble("ToLatitude");
				assertEquals(isTrue(k,p), true);
				
				assertEquals(rs.getBoolean("Type"), true);
				stmt.executeUpdate("DELETE FROM WantToGo WHERE UserID =" + ID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			}else
				assertEquals(1,2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test2() {
		try{
			UserConnection user = new UserConnection((BasicDataSource) source);
			user.insertIntoUsers("Tedo", "Chubinidze", "tedochubinidze@yahoo.com", "123", true, "'1994-02-04'");
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			int ID = 0;
			ResultSet rs = stmt.executeQuery("SELECT ID FROM Users Where EMail = 'tedochubinidze@yahoo.com'");
			if (rs.isBeforeFirst()) {
				rs.next();
				ID = rs.getInt("ID");
			}
			WantToGoConnection want = new WantToGoConnection((BasicDataSource) source);
			want.insertIntoRequestss(ID, "casvla mindaa", 1.4, 4.5, 1.3, 4.2, true);
			int WantID = 0;
			rs = stmt.executeQuery("SELECT ID FROM WantToGo Where UserID = " + ID);
			if (rs.isBeforeFirst()) {
				rs.next();
				WantID = rs.getInt("ID");
			}
			want.insertIntoWantToGoDates(WantID, "1970-01-01 00:00:01", "1979-01-01 00:00:01");
			want.CloseConnection();
			user.CloseConnection();
			rs = stmt.executeQuery("SELECT * FROM WantToGoDates where wantToGoID = " + WantID);
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(rs.getTimestamp("StartDate"), Timestamp.valueOf("1970-01-01 00:00:01"));
				assertEquals(rs.getTimestamp("EndDate"), Timestamp.valueOf("1979-01-01 00:00:01"));
				stmt.executeUpdate("DELETE FROM WantToGoDates WHERE wantToGoID = " + WantID);
				stmt.executeUpdate("DELETE FROM WantToGo WHERE UserID =" + ID);
				stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
			}else
				assertEquals(1,2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	@Test
		public void test3() {
			try{		
				UserConnection user = new UserConnection((BasicDataSource) source);
				user.insertIntoUsers("Tedo", "Chubinidze", "tedochubinidze@yahoo.com", "123", true, "'1994-02-04'");
				Connection con = source.getConnection();
				Statement stmt = con.createStatement();
				stmt.executeQuery("USE " + database);
				int ID = 0;
				ResultSet rs = stmt.executeQuery("SELECT ID FROM Users Where EMail = 'tedochubinidze@yahoo.com'");
				if (rs.isBeforeFirst()) {
					rs.next();
					ID = rs.getInt("ID");
				}
				WantToGoConnection want = new WantToGoConnection((BasicDataSource) source);
				want.insertIntoRequestss(ID, "casvla mindaa", 1.4, 4.5, 1.3, 4.2, true);
				int WantID = 0;
				rs = stmt.executeQuery("SELECT ID FROM WantToGo Where UserID = " + ID);
				if (rs.isBeforeFirst()) {
					rs.next();
					WantID = rs.getInt("ID");
				}
				want.insertIntoWantToGoEveryday(WantID, "1979-01-01 00:00:01", "1971-01-01 00:00:01", 3);
				want.CloseConnection();
				user.CloseConnection();
				rs = stmt.executeQuery("SELECT * FROM WantToGoEveryday where wantToGoID = " + WantID);
				if (rs.isBeforeFirst()) {
					rs.next();
					assertEquals(rs.getInt("Day"), 3);
					assertEquals(rs.getTimestamp("StartDate"), Timestamp.valueOf("1979-01-01 00:00:01"));
					assertEquals(rs.getTimestamp("EndDate"), Timestamp.valueOf("1971-01-01 00:00:01"));
					stmt.executeUpdate("DELETE FROM WantToGoEveryday WHERE wantToGoID = " + WantID);
					stmt.executeUpdate("DELETE FROM WantToGo WHERE UserID =" + ID);
					stmt.executeUpdate("DELETE FROM Users Where ID = " + ID);
				}else
					assertEquals(1,2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
		}
}
