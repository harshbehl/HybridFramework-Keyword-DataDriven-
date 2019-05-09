package com.datadriven.listeners;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {
	Logger log = Logger.getLogger(TestListeners.class);
	@Override
	public void onTestStart(ITestResult result) {
		
		
		log.info("The test method " + result.getMethod().getMethodName()+" Started");
		log.info("The Start time " +result.getStartMillis());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("The test method " + result.getMethod().getMethodName()+" is pass");
		log.info("The End time " +result.getEndMillis());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		
	}

}
