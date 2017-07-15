package utility.geometry;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith ( Suite.class )
@SuiteClasses
(
		{	
			PointBuilderTest.class,
			LineTest.class,
			LineBuilderTest.class,
			CircleTest.class,
			CircleBuilderTest.class,
		}
			)
public class GeometryTest
{
}
