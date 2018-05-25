package de.ulikoenig.bahnwatch.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import de.ulikoenig.bahnwatch.TrainStop;
import de.ulikoenig.bahnwatch.util.Util;

public class TrainStopTest {
	TrainStop eins = new TrainStop("Hamburg Hbf",7, 8);
	TrainStop zwei = new TrainStop("Hamburg Hbf",7, 8);
	TrainStop drei = new TrainStop("L\\u00fcbeck Hbf",7, 8);
	
	
	public void setUp() {
		
	}

	@Test
	public void testHashCode() {
		assertEquals(eins.hashCode(),eins.hashCode());
		assertEquals(eins.hashCode(),zwei.hashCode());
		assertNotEquals(eins.hashCode(),drei.hashCode());
	}

	@Test
	public void testCompareTo() {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.set(2000,1, 1, 10,10);
		c2.set(2000,1, 1, 10,10);
		c1.set(Calendar.MILLISECOND,0);
		c2.set(Calendar.MILLISECOND,0);
		assertEquals(0,c1.compareTo(c2) );
		
		Date d1 = c1.getTime();
		Date d2 = c2.getTime();
		assertEquals(d1.getTime(), d2.getTime());
		Calendar calThis = Util.getDayCalender( 7,8);
		
		Calendar calOther = Util.getDayCalender(  7, 8);
		
		assertTrue(calThis.equals(calOther));
		
		assertEquals(calThis.compareTo(calOther),0);
		assertEquals(eins.compareTo(eins),0);
		assertEquals(eins.compareTo(zwei),0);
		assertNotEquals(eins.compareTo(drei),0);
	}

	@Test
	public void testEqualsObject() {
		assertEquals(eins,eins);
		assertEquals(eins,zwei);
		assertNotEquals(eins,drei);
	}

}
