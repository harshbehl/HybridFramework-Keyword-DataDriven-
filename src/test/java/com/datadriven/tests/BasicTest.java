package com.datadriven.tests;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.datadriven.base.TestBase;
import com.datadriven.reporting.ExtentReporting;

public class BasicTest extends TestBase{
	
	 private static Logger log=Logger.getLogger(BasicTest.class);

	
	@Test()
	public void sampleTest() throws IOException
	{
		

	   
		getDriver().get("https://google.com");
		
		ExtentReporting.getTest().pass("Navigated to google.com successfully",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("Navigated to Screen")).build());
      
		setText("css_google.homepage.searchText", "Jatin Behl");
		
		submitForm("xpath_google.homepage.searchButton");
		ExtentReporting.getTest().pass("search Button Clicked successfully");
         Assert.fail();
	}

	@Test()
	public void sampleTest1() throws IOException
	{
		

		ExtentReporting.getTest().info("Navigating to https://google.com");
		getDriver().get("https://google.com");
		
		ExtentReporting.getTest().pass("Navigated to google.com successfully",MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("Navigated to Screen")).build());
      
		setText("css_google.homepage.searchText", "Jatin Behl");
		//ExtentReporting.passStep("Text Entered Successfully", false, null);
		submitForm("xpath_google.homepage.searchButton");
		ExtentReporting.getTest().pass("search Button Clicked successfully");
	
		
	}

}
