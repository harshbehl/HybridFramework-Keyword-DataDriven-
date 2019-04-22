package com.datadriven.reporting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.datadriven.base.Page;

public class ExtentReporting extends Page {

	private static ExtentHtmlReporter reporter;
	static Calendar cal = null;
	static Date date = null;
	static DateFormat sdate = null;
	private static ExtentReports reportParam;
	private static ThreadLocal<ExtentTest> tests = new ThreadLocal<ExtentTest>();
	private static ExtentTest test;

	public static void setReporter() {
		cal = Calendar.getInstance();
		date = cal.getTime();
		sdate = new SimpleDateFormat("HH:mm:ss");
		String fDate = sdate.format(date);
		fDate = fDate.replace(":", "_");
		reporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\test-output\\ExtentReports\\" + "ExtentReport" + fDate + ".html");
		reporter.setAppendExisting(true);
	}

	public static void setExtent() {
		reportParam = new ExtentReports();
		reportParam.setSystemInfo("Operating System", "Windows 8");
		reportParam.setSystemInfo("Java Version", "8");

		reportParam.attachReporter(reporter);

	}

	public static ExtentReports getExtent() {
		return reportParam;

	}

	public static void CreateTestCase(String TestCaseName, String Author, String Category) {
		test = getExtent().createTest(TestCaseName);
		test.assignAuthor(Author);
		test.assignAuthor(Category);
		tests.set(test);
	}

	public static ExtentTest getTest() {
		return tests.get();
	}

	public static void flush() {
		getExtent().flush();
	}
}
