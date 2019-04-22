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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.datadriven.reporting.ExtentReporting;

public class TestBase extends Page {
	WebDriver driver = null;



	@BeforeMethod
	public void beforeMethod(Method testngTest) throws FileNotFoundException, IOException {
		System.setProperty("webdriver.gecko.driver", "E:\\Automation\\Drivers\\FirefoxDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		setDriver(driver);
		setLocProperties();
        ExtentReporting.CreateTestCase(testngTest.getName(),"Harsh Behl","Regression");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.isSuccess()) {
			getDriver().quit();
			fis.close();
			ExtentReporting.flush();
		} else if (result.getStatus() == 3) {
			ExtentReporting.getTest().skip("The Test Case" +result.getMethod()+" is skipped");
			getDriver().quit();
			fis.close();
			ExtentReporting.flush();

		} else if (result.getStatus() == 2) {
			ExtentReporting.getTest().log(Status.FAIL,
			result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("FailedScreenshot")).build());
			getDriver().quit();
			fis.close();
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
		/*
		 * reporter = null; report = null;
		 */
	}

}
