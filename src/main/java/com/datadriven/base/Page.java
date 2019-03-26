package com.datadriven.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.datadriven.listeners.DriverListeners;

public class Page {
	private static EventFiringWebDriver eventDriver;
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	static DriverListeners dListener = new DriverListeners();

	protected static void setDriver(WebDriver driver) {
		eventDriver = new EventFiringWebDriver(driver);
		eventDriver.register(dListener);
		webDriver.set(eventDriver);

	}

	protected static WebDriver getDriver() {
		return webDriver.get();
	}

	protected static WebElement getWebElement(String xPath) {
		return getDriver().findElement(By.xpath(xPath));
	}

	protected static boolean isElementExists(String xPath) {
		if (getDriver().findElements(By.xpath(xPath)).size() != 0)
			return true;
		else
			return false;

	}

	protected static boolean isElementEnabled(String xPath) {
		if (getDriver().findElement(By.xpath(xPath)).isEnabled())
			return true;
		else
			return false;
	}

	protected static boolean isElementDisplayed(String xPath) {
		if (getDriver().findElement(By.xpath(xPath)).isDisplayed())
			return true;
		else
			return false;

	}

	protected static boolean isElementSelected(String xPath) {
		if (getDriver().findElement(By.xpath(xPath)).isSelected())
			return true;
		else
			return false;

	}

}
