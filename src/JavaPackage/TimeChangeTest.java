package JavaPackage;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeChangeTest {

	@Test
	public void test() {
		String obj = TimeChange.getCorrectDate("1994-02-14");
		String obj1 = TimeChange.getCorrectDate("164-02-14");
		String obj2 = TimeChange.getCorrectDate("2002-12-31");
		String obj3 = TimeChange.getCorrectDate("1964-02-1");
		assertEquals("14-02-1994", obj);
		assertEquals("14-02-164", obj1);
		assertEquals("31-12-2002", obj2);
		assertEquals("1-02-1964", obj3);
	}

}
