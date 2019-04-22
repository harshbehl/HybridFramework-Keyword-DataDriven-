import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.datadriven.base.TestBase;

public class BasicTest extends TestBase{
	
	 private static Logger log=Logger.getLogger(BasicTest.class);

	
	@Test()
	public void sampleTest() throws IOException
	{
		

	  test.info("Navigating to https://google.com");
		getDriver().get("https://google.com");
		test.pass("Navigated to google.com successfully");

		setText("css_google.homepage.searchText", "Jatin Behl");
		test.pass("Text entered successfully");
		submitForm("xpath_google.homepage.searchButton");
		test.pass("search Button Clicked successfully");
		
	}


}
