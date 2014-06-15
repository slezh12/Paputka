package JavaPackage;

import static org.junit.Assert.*;

import java.sql.Date;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

public class UserParseInfoTest {

	private DataSource source;
	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;
	
	
	@Before
	public void setUp(){
		source = new BasicDataSource();
    	((BasicDataSource) source).setDriverClassName("com.mysql.jdbc.Driver");
    	((BasicDataSource) source).setUsername(account);
    	((BasicDataSource) source).setPassword(password);
    	((BasicDataSource) source).setUrl("jdbc:mysql://"+server+":3306/"+database);
	}
	
	/* gaketebulia
	 * insert into users (FirstName, LastName, Gender, BirthDate, Password, EMail)
values (N'achi', N'baxlosania', true, '1994-08-23' ,  N'40bd001563085fc35165329ea1ff5c5ecbdbbeef', N'achi_baxlosania@yahoo.com');
	 */
	@Test
	public void testGetUser() {
		
		UserParseInfo info = new UserParseInfo((BasicDataSource) source);
		
		User newuser = info.getUser("achi_baxlosania@yahoo.com", "123");
		assertEquals(Date.valueOf("1994-08-23"),newuser.getBirthdate());
		assertEquals("achi",newuser.getFirstName());
		assertEquals("baxlosania",newuser.getLastName());
		assertEquals(true,newuser.getGender());
		assertEquals("achi_baxlosania@yahoo.com",newuser.getEmail());
	}

}
