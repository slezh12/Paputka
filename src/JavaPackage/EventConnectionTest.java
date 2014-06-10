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

public class EventConnectionTest {

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
	
	private boolean areDoublesEqual(double a,double b){
		if(a == b){
			return true;
		}
		return false;
	}
	
	@Test
	public void testInsertIntoEvents() {
		EventConnection base = new EventConnection((BasicDataSource) source);
		base.insertIntoEvents(1, 5, 10, 1.1, 2.1, 3.1, 4.1, "tbilisi", "batumi", false, true);
		base.CloseConnection();
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM Events where ID = 1");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(1,rs.getInt("userID"));
				assertEquals(5,rs.getInt("places"));
				double fee = rs.getDouble("fee");
				double FromLongitude = rs.getDouble("FromLongitude");
				double FromLatitude = rs.getDouble("FromLatitude");
				double ToLongitude = rs.getDouble("ToLongitude");
				double ToLatitude = rs.getDouble("ToLatitude");
				assertEquals(true,areDoublesEqual(10,fee));
				assertEquals(true,areDoublesEqual(1.1,FromLongitude));
				assertEquals(true,areDoublesEqual(2.1,FromLatitude));
				assertEquals(true,areDoublesEqual(3.1,ToLongitude));
				assertEquals(true,areDoublesEqual(4.1,ToLatitude));
				assertEquals("tbilisi",rs.getString("fromPlace") );
				assertEquals("batumi",rs.getString("toPlace"));
				assertEquals(false,rs.getBoolean("type"));
				assertEquals(true,rs.getBoolean("validation"));
			}else
				assertEquals(1,2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
