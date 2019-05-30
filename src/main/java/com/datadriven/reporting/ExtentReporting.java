package com.datadriven.reporting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.datadriven.base.Page;

public class ExtentReporting extends Page {

	private static ExtentHtmlReporter reporter;
	static Calendar cal = null;
	static Date date = null;
	static DateFormat sdate = null;
	private static ExtentReports reportParam;
	private static ExtentTest test;

	public static void setReporter() {
		cal = Calendar.getInstance();
		date = cal.getTime();
		sdate = new SimpleDateFormat("HH:mm:ss");
		String fDate = sdate.format(date);
		fDate = fDate.replace(":", "_");
		if (reporter == null) {
			reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\"
					+ "ExtentReport" + fDate + ".html");
			reporter.setAppendExisting(true);
			reporter.config().setChartVisibilityOnOpen(true);
			reporter.config().setDocumentTitle("Keyword Driven Framework Automation Report");
			reporter.config().setTheme(Theme.DARK);
			reportParam = new ExtentReports();
			reportParam.setSystemInfo("Operating System", "Windows 8");
			reportParam.setSystemInfo("Java Version", "8");

			reportParam.attachReporter(reporter);
		}
	}

	public static void CreateTestCase(String TestCaseName, String Author, String Category) {
		test = reportParam.createTest(TestCaseName);
		test.assignAuthor(Author);
		test.assignAuthor(Category);

	}
	
	public static ExtentTest getTest()
	{
		return test;
	}

	public static void flush() {
		reportParam.flush();
	}
}
