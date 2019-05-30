package com.datadriven.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.datadriven.reporting.ExtentReporting;
import com.datadriven.utilities.ExcelUtility;

public class TestBase extends Page {
	public String testCaseName;
	public Properties locProp;
	public Properties envProp;
	public ExcelUtility util;
	private FileInputStream fisLoc;
	private FileInputStream fisEnv;
	public DriverScript driverScript;
	
	
	@BeforeSuite
	public void init() throws FileNotFoundException {
		util = new ExcelUtility(Constants.EXCEL_SUITE_PATH+"\\"+Constants.TEST_SUITE_NAME+".xlsx");
		fisLoc = new FileInputStream(new File(Constants.LOC_PROP_PATH));
		fisEnv = new FileInputStream(new File(Constants.ENV_PROP_PATH));
		ExtentReporting.setReporter();

	}

	@BeforeTest
	public void setUp() {
		driverScript=new DriverScript();
		testCaseName = this.getClass().getSimpleName();
		locProp = new Properties();
		envProp = new Properties();
		try {
			locProp.load(fisLoc);
			envProp.load(fisEnv);
			driverScript.setEnvProp(envProp);
			driverScript.setLocProp(locProp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentReporting.CreateTestCase(testCaseName, "Harsh Behl", "Regression");

	}
	
	@BeforeMethod
	public void createTestCase()
	{ExtentReporting.CreateTestCase(testCaseName, "Harsh Behl", "Regression");
		
	}
	
	@AfterMethod
	public void quitDriver()
	{   ExtentReporting.flush();
		driverScript.quit();	
		
	}
	
	@AfterTest
	public void tearUpResources() 
	{    if(fisLoc!=null)
		try {
			fisLoc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	if(fisEnv!=null)
		try {
			fisEnv.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@DataProvider(name = "DataProvider")
	public Object[][] getTestData()
	{  return util.extractTestData(testCaseName, Constants.TEST_DATA_SHEET);
		
		
	}
}
