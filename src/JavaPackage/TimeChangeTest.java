package JavaPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeChangeTest {

	@Test
	public void test() {
		String obj = TimeChange.getCorrectDate("14-02-1994");
		String obj1 = TimeChange.getCorrectDate("31-12-2002");
		assertEquals("1994-02-14", obj);
		assertEquals("2002-12-31", obj1);
	}
	@Test
	public void test2(){
		String obj = TimeChange.getCorrectDateForFacebook("09/15/1994");
		String obj1 = TimeChange.getCorrectDateForFacebook("11/16/1993");
		String obj2 = TimeChange.getCorrectDateForFacebook("04/11/1994");
		assertEquals("1994-09-15", obj);
		assertEquals("1993-11-16", obj1);
		assertEquals("1994-04-11", obj2);


	}

}
