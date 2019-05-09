package com.datadriven.tests;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.datadriven.base.Constants;
import com.datadriven.base.TestBase;


public class SuiteA extends TestBase {

	

	@Test(dataProvider = "DataProvider")
	public void LoginTest(Hashtable<String,String> testData)  {
        
	
		
	}
	@Test(dataProvider = "DataProvider")
	public void SignUpTest(Hashtable<String,String> testData)  {
        
	
		
	}
	
	@Test(dataProvider = "DataProvider")
	public void SignUpTest2(Hashtable<String,String> testData)  {
        
	
		
	}
	
	@DataProvider(name = "DataProvider")
	public Object[][] getTestData(Method m)
	{  return util.extractTestData(m.getName(), Constants.TEST_DATA_SHEET);
		
		
	}

}
