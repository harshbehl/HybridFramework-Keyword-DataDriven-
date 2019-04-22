package com.datadriven.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase extends Page {
	WebDriver driver = null;
	ExtentHtmlReporter reporter = null;
	ExtentReports report = null;

	public ExtentTest test = null;

	@BeforeMethod
	public void beforeMethod(Method testngTest) throws FileNotFoundException, IOException {
		System.setProperty("webdriver.gecko.driver", "E:\\Automation\\Drivers\\FirefoxDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		setDriver(driver);
		setLocProperties();

		test = report.createTest(testngTest.getName());
		test.assignAuthor("Harsh Behl");
		test.assignCategory("Regression");

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.isSuccess()) {
			getDriver().quit();
			fis.close();
			report.flush();
		} else if (result.getStatus() == 3) {
			test.skip("The Test Case is Skipped");
			getDriver().quit();
			fis.close();
			report.flush();
			
		} else if (result.getStatus() == 2) {
			test.fail("The Test case is failed.");
			report.flush();
			getDriver().quit();
			fis.close();
		
			
		}
	}

	@BeforeSuite
	public void beforeSuite() {
		reporter = new ExtentHtmlReporter(
				"E:\\SeleniumFrameworks\\DataDrivenFrameworks\\test-output\\ExtentReports\\Extent.html");
		report = new ExtentReports();
		report.attachReporter(reporter);

	}

	@AfterSuite
	public void afterSuite() {
		reporter = null;
		report = null;

	}

}
