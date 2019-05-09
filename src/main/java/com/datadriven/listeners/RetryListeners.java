package com.datadriven.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.datadriven.base.Constants;

public class RetryListeners implements IRetryAnalyzer {
	private int initCount = 0;

	@Override
	public boolean retry(ITestResult result) {
		boolean tresult = false;
		if (!result.isSuccess()) {
			if (initCount < Constants.RETRY_COUNT) {
				initCount++;
				tresult = true;

			}
		}
		return tresult;

	}

}