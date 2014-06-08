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
	public void SetUp(){
		source = new BasicDataSource();
    	((BasicDataSource) source).setDriverClassName("com.mysql.jdbc.Driver");
    	((BasicDataSource) source).setUsername(account);
    	((BasicDataSource) source).setPassword(password);
    	((BasicDataSource) source).setUrl("jdbc:mysql://"+server+":3306/"+database);
	}
	
	/* gaketebulia
	 * insert into users (FirstName, LastName, Gender, BirthDate,  Password, EMail)
values (N'achi', N'baxlosania', true, '1994-08-23' ,  N'40bd001563085fc35165329ea1ff5c5ecbdbbeef', N'achi_baxlosania@yahoo.com');
	 */
	@Test
	public void test() {
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		try {
			ResultSet rs = base.getInfoByMail("achi_baxlosania@yahoo.com");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(rs.getString("Password"), "40bd001563085fc35165329ea1ff5c5ecbdbbeef");
				assertEquals(rs.getString("FirstName"), "achi");
				assertEquals(rs.getString("LastName"), "baxlosania");
				assertEquals(rs.getBoolean("Gender"),true);
				assertEquals(rs.getDate("BirthDate"), Date.valueOf("1994-08-23"));
			}else{
				assertEquals(1,2);
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testInsertIntoUsers() {	
		BaseConnection base = new BaseConnection((BasicDataSource) source);
		String password1 = Hash.calculateHashCode("123456");
		Date date = Date.valueOf("1994-02-04");
		base.insertIntoUsers("Beqa", "Khaburdzania", "bkhab12@freeuni.edu.ge", password1, true, date);
		base.CloseConnection();
		try {
			Connection con = source.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users where EMail = 'bkhab12@freeuni.edu.ge'");
			if (rs.isBeforeFirst()) {
				rs.next();
				assertEquals(rs.getString("Password"), password1);
				assertEquals(rs.getString("FirstName"), "Beqa");
				assertEquals(rs.getString("LastName"), "Khaburdzania");
				assertEquals(rs.getBoolean("Gender"),true);
				assertEquals(rs.getDate("BirthDate"), date);
				stmt.executeUpdate("DELETE FROM Users WHERE EMail = 'bkhab12@freeuni.edu.ge'");
			}else
				assertEquals(1,2);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
