package com.datadriven.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.datadriven.reporting.ExtentReporting;
import com.datadriven.utilities.ExcelUtility;

public class TestBase extends Page {
	WebDriver driver = null;

	protected ExcelUtility util = new ExcelUtility(Constants.EXCEL_SUITE_PATH+"\\"+Constants.TEST_SUITE_NAME+".xlsx");
	

	public void beforeMethod(Method testngTest) throws FileNotFoundException, IOException {
		;
		System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_PATH);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Constants.WEBDRIVER_IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		setDriver(driver);
		
       
   
		setLocProperties();
		ExtentReporting.CreateTestCase(testngTest.getName(), "Harsh Behl", "Regression");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.isSuccess()) {
			tearUp();
			ExtentReporting.flush();
		} else if (result.getStatus() == 3) {
			ExtentReporting.getTest().skip("The Test Case" + result.getMethod() + " is skipped");

			tearUp();
			ExtentReporting.flush();

		} else if (result.getStatus() == 2) {
			ExtentReporting.getTest().log(Status.FAIL, result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("FailedScreenshot")).build());
			tearUp();
			ExtentReporting.flush();

		}
	}

	@BeforeSuite
	public void beforeSuite() {
		ExtentReporting.setReporter();
		ExtentReporting.setExtent();

	}

	@AfterSuite
	public void afterSuite() {

	}

}
