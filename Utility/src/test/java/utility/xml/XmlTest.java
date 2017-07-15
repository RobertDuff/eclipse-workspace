package utility.xml;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.TestCase;

@RunWith ( Suite.class )
@SuiteClasses 
(
		{
				XmlNodeTest.class,
				XmlLoaderTest.class,
		}
		)
public class XmlTest extends TestCase
{
}
