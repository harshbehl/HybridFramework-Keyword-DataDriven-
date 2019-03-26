package com.datadriven.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListeners implements IRetryAnalyzer {
	private int initCount = 0;
	private int retryCount = 3;

	@Override
	public boolean retry(ITestResult result) {
		boolean tresult = false;
		if (result.isSuccess()) {
			tresult = true;
		} else if (!result.isSuccess() && initCount < retryCount) {

			initCount++;
		}
		return tresult;

	}
}