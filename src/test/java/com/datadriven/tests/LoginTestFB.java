package com.datadriven.tests;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.datadriven.base.Constants;
import com.datadriven.base.TestBase;

public class LoginTestFB extends TestBase {

	@Test(dataProvider = "DataProvider")
	public void test_LoginTest(Hashtable<String, String> testData) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (testData.get(Constants.RUN_MODE_COL).equalsIgnoreCase("N") | util.checkRunMode(testCaseName))
			throw new SkipException(testCaseName + " is Skipped");
		driverScript.runDriver(util, testData, testCaseName);

	}

}
