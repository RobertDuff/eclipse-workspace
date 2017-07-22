package utility.geometry;

import static org.junit.Assert.*;

import org.junit.Test;


public class LineSegmentTest
{
	private static final double TOLERANCE = 0.000000001;

	@Test
	public void testLine ()
	{
		LineSegment l;
		
		l = new LineSegment ( 1, 1, 2, 2 );		
		assertEquals ( Math.PI / 4, l.line ().theta (), TOLERANCE );
	}

	@Test
	public void testLength ()
	{
		LineSegment l;
		
		l = new LineSegment ( 1, 1, 2, 2 );
		assertEquals ( Math.sqrt ( 2 ), l.length (), TOLERANCE );		
	}
}
