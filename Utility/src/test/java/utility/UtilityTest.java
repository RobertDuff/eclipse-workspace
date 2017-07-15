package utility;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import utility.arrays.ArrayConverterTest;
import utility.expression.ExprTest;
import utility.geometry.GeometryTest;
import utility.join.JoinTest;
import utility.protocol.ProtocolTestSuite;
import utility.state.StateTest;
import utility.stopwatch.StopWatchTest;
import utility.xml.XmlTest;

@RunWith ( Suite.class )
@SuiteClasses 
(
		{
				ArrayConverterTest.class,
				ExprTest.class,
				GeometryTest.class,
				JoinTest.class,
				ProtocolTestSuite.class,
				StateTest.class,
				StopWatchTest.class,
				XmlTest.class,
		}
		)
public class UtilityTest
{
}
