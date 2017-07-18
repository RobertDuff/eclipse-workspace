package binaryclock;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BinaryTime
{
	private static int NUMBER_OF_DIGITS = 6;
	private static int BITS_PER_DIGIT = 4;
	
	protected BooleanProperty is24Hour;
	
	private boolean[][] bits = new boolean[ NUMBER_OF_DIGITS ][ BITS_PER_DIGIT ];
	
	BinaryTime()
	{
		is24Hour = new SimpleBooleanProperty ( false );
		update();
	}
	
	public boolean[][] getBits()
	{
		return bits;
	}
	
	public BooleanProperty get24HourProperty()
	{
		return is24Hour;
	}
	
	public void update()
	{
		update ( new GregorianCalendar () );
	}
	
	public void update ( Calendar time )
	{
		int hour;
		
		if ( is24Hour.get () )
		{
			hour = time.get ( Calendar.HOUR_OF_DAY );
		}
		else
		{
			hour = time.get ( Calendar.HOUR );
		
			// Calendar HOUR is zero based, so convert to one based.
			if ( hour == 0 )
				hour = 12;
		}
		
		int min = time.get ( Calendar.MINUTE );
		int sec = time.get ( Calendar.SECOND );

		fillBits ( bits[ 0 ], hour / 10 );
		fillBits ( bits[ 1 ], hour % 10 );

		fillBits ( bits[ 2 ], min / 10 );
		fillBits ( bits[ 3 ], min % 10 );

		fillBits ( bits[ 4 ], sec / 10 );
		fillBits ( bits[ 5 ], sec % 10 );
	}
	
	private void fillBits ( boolean[] row, int digit )
	{
		row[ 0 ] = ( digit & 0x08 ) != 0;
		row[ 1 ] = ( digit & 0x04 ) != 0;
		row[ 2 ] = ( digit & 0x02 ) != 0;
		row[ 3 ] = ( digit & 0x01 ) != 0;
	}
}
