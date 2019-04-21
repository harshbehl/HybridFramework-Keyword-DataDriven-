import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.datadriven.base.Page;
import com.datadriven.listeners.DriverListeners;

public class BasicTest extends Page{
	
	 private static Logger log=Logger.getLogger(BasicTest.class);
	@BeforeMethod()
	public void setupDriver() throws IOException
	{	System.setProperty("webdriver.gecko.driver", "E:\\Automation\\Drivers\\FirefoxDriver\\geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		setDriver(driver);
	}
	
	@Test()
	public void sampleTest() throws IOException
	{
		log.info("running sample test");

	  
		getDriver().get("https://google.com");
		setText("css_google.homepage.searchText", "Jatin Behl");
		submitForm("xpath_google.homepage.searchButton");
	}

	@AfterMethod()
	public void tearUp()
	{
		//getDriver().quit();
	}
}
