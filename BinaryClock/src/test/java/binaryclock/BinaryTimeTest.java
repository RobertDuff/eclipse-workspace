package binaryclock;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class BinaryTimeTest
{
	@Test
	public void testMidnight ()
	{
		Calendar time = new GregorianCalendar ();
		
		time.set ( Calendar.HOUR, 12 );
		time.set ( Calendar.MINUTE, 0 );
		time.set ( Calendar.SECOND, 0 );
		
		BinaryTime b = new BinaryTime();
		
		b.update ( time );
		boolean[][] bits = b.getBits ();
		
		// Hour ( 0001 0010 )
		
		assertEquals ( false, bits[ 0 ][ 0 ] );
		assertEquals ( false, bits[ 0 ][ 1 ] );
		assertEquals ( false, bits[ 0 ][ 2 ] );
		assertEquals ( true,  bits[ 0 ][ 3 ] );
		
		assertEquals ( false, bits[ 1 ][ 0 ] );
		assertEquals ( false, bits[ 1 ][ 1 ] );
		assertEquals ( true,  bits[ 1 ][ 2 ] );
		assertEquals ( false, bits[ 1 ][ 3 ] );
		
		// Minute ( 0000 0000 )
		
		assertEquals ( false, bits[ 2 ][ 0 ] );
		assertEquals ( false, bits[ 2 ][ 1 ] );
		assertEquals ( false, bits[ 2 ][ 2 ] );
		assertEquals ( false, bits[ 2 ][ 3 ] );
		
		assertEquals ( false, bits[ 3 ][ 0 ] );
		assertEquals ( false, bits[ 3 ][ 1 ] );
		assertEquals ( false, bits[ 3 ][ 2 ] );
		assertEquals ( false, bits[ 3 ][ 3 ] );
		
		// Second ( 0000 0000 )
		
		assertEquals ( false, bits[ 4 ][ 0 ] );
		assertEquals ( false, bits[ 4 ][ 1 ] );
		assertEquals ( false, bits[ 4 ][ 2 ] );
		assertEquals ( false, bits[ 4 ][ 3 ] );
		
		assertEquals ( false, bits[ 5 ][ 0 ] );
		assertEquals ( false, bits[ 5 ][ 1 ] );
		assertEquals ( false, bits[ 5 ][ 2 ] );
		assertEquals ( false, bits[ 5 ][ 3 ] );
	}

	@Test
	public void testAllSingleDigits ()
	{
		Calendar time = new GregorianCalendar ();
		
		time.set ( Calendar.HOUR, 3 );
		time.set ( Calendar.MINUTE, 7 );
		time.set ( Calendar.SECOND, 8 );
		
		BinaryTime b = new BinaryTime();
		
		b.update ( time );
		boolean[][] bits = b.getBits ();
		
		// Hour ( 0000 0011 )
		
		assertEquals ( false, bits[ 0 ][ 0 ] );
		assertEquals ( false, bits[ 0 ][ 1 ] );
		assertEquals ( false, bits[ 0 ][ 2 ] );
		assertEquals ( false, bits[ 0 ][ 3 ] );
		
		assertEquals ( false, bits[ 1 ][ 0 ] );
		assertEquals ( false, bits[ 1 ][ 1 ] );
		assertEquals ( true,  bits[ 1 ][ 2 ] );
		assertEquals ( true,  bits[ 1 ][ 3 ] );
		
		// Minute ( 0000 0111 )
		
		assertEquals ( false, bits[ 2 ][ 0 ] );
		assertEquals ( false, bits[ 2 ][ 1 ] );
		assertEquals ( false, bits[ 2 ][ 2 ] );
		assertEquals ( false, bits[ 2 ][ 3 ] );
		
		assertEquals ( false, bits[ 3 ][ 0 ] );
		assertEquals ( true,  bits[ 3 ][ 1 ] );
		assertEquals ( true,  bits[ 3 ][ 2 ] );
		assertEquals ( true,  bits[ 3 ][ 3 ] );
		
		// Second ( 0000 1000 )
		
		assertEquals ( false, bits[ 4 ][ 0 ] );
		assertEquals ( false, bits[ 4 ][ 1 ] );
		assertEquals ( false, bits[ 4 ][ 2 ] );
		assertEquals ( false, bits[ 4 ][ 3 ] );
		
		assertEquals ( true, bits[ 5 ][ 0 ] );
		assertEquals ( false, bits[ 5 ][ 1 ] );
		assertEquals ( false, bits[ 5 ][ 2 ] );
		assertEquals ( false, bits[ 5 ][ 3 ] );
	}

	@Test
	public void testAllMultipleDigits ()
	{
		Calendar time = new GregorianCalendar ();
		
		time.set ( Calendar.HOUR, 11 );
		time.set ( Calendar.MINUTE, 42 );
		time.set ( Calendar.SECOND, 55 );
		
		BinaryTime b = new BinaryTime();
		
		b.update ( time );
		boolean[][] bits = b.getBits ();
		
		// Hour ( 0001 0001 )
		
		assertEquals ( false, bits[ 0 ][ 0 ] );
		assertEquals ( false, bits[ 0 ][ 1 ] );
		assertEquals ( false, bits[ 0 ][ 2 ] );
		assertEquals ( true,  bits[ 0 ][ 3 ] );
		
		assertEquals ( false, bits[ 1 ][ 0 ] );
		assertEquals ( false, bits[ 1 ][ 1 ] );
		assertEquals ( false, bits[ 1 ][ 2 ] );
		assertEquals ( true,  bits[ 1 ][ 3 ] );
		
		// Minute ( 0010 0010 )
		
		assertEquals ( false, bits[ 2 ][ 0 ] );
		assertEquals ( true,  bits[ 2 ][ 1 ] );
		assertEquals ( false, bits[ 2 ][ 2 ] );
		assertEquals ( false, bits[ 2 ][ 3 ] );
		
		assertEquals ( false, bits[ 3 ][ 0 ] );
		assertEquals ( false, bits[ 3 ][ 1 ] );
		assertEquals ( true,  bits[ 3 ][ 2 ] );
		assertEquals ( false, bits[ 3 ][ 3 ] );
		
		// Second ( 0101 0101 )
		
		assertEquals ( false, bits[ 4 ][ 0 ] );
		assertEquals ( true,  bits[ 4 ][ 1 ] );
		assertEquals ( false, bits[ 4 ][ 2 ] );
		assertEquals ( true,  bits[ 4 ][ 3 ] );
		
		assertEquals ( false, bits[ 5 ][ 0 ] );
		assertEquals ( true,  bits[ 5 ][ 1 ] );
		assertEquals ( false, bits[ 5 ][ 2 ] );
		assertEquals ( true,  bits[ 5 ][ 3 ] );
	}
}
