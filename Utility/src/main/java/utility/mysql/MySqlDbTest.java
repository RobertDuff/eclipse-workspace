package utility.mysql;

import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MySqlDbTest
{
	@Before
	public void setUp () throws Exception
	{}

	@After
	public void tearDown () throws Exception
	{}

	@Test
	public final void test () 
	{
		MySqlDb db = null;
		
		try
		{
			db = new MySqlDb ( "test" );
			
			Statement stmt = db.createStatement();
			if ( stmt.execute ( "select * from jackie" ) )
			{
				ResultSet r = stmt.getResultSet();
				
				while ( r.next () )
				{
					String name = r.getString ( "name" );
					System.out.printf ( "NAME={%s}\n", name );
				}
			}
			else
			{
				fail ( "Statement Execute Failed" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			fail ( "Exception" );
		}
		finally
		{
			try
			{
				if ( db != null )
					db.close();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				fail ( "Connection.close() Exception" );
			}
		}
	}
}
