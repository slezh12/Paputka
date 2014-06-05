package JavaPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParseInfoTest {

	
	
	/* gaketebulia
	 * insert into users (FirstName, LastName, Gender, BirthDate, Status, Password, EMail)
values (N'achi', N'baxlosania', true, '1994-08-23' , N'statusi', N'40bd001563085fc35165329ea1ff5c5ecbdbbeef', N'achi_baxlosania@yahoo.com');
	 */
	@Test
	public void testGetUser() {
		User newuser = ParseInfo.getUser("achi_baxlosania@yahoo.com", "123");
	}
}
