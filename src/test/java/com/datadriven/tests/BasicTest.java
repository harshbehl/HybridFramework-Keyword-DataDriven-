package com.datadriven.tests;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.datadriven.base.TestBase;
import com.datadriven.utilities.ExcelUtility;

public class BasicTest extends TestBase {

	ExcelUtility util = new ExcelUtility("E:\\New Microsoft Excel Worksheet.xlsx");

	@Test(dataProvider = "LoginData")
	public void Login(Hashtable<String,String> testData)  {
        System.out.println(util.getCurrentWB().getSheetAt(1).getLastRowNum());
		System.out.println(testData.get("UserName"));
		System.out.println(testData.get("Password"));
		System.out.println(testData.get("Browser"));
	
		
	}
	
	
	@DataProvider(name = "LoginData")
	public Object[][] getTestData()
	{  return util.extractTestData("SignUpTest2", "DataSheet");
		
		
	}

}
