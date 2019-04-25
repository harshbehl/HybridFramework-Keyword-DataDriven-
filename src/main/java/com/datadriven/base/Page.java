package com.datadriven.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.datadriven.listeners.DriverListeners;

public class Page {
	private static EventFiringWebDriver eventDriver;
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private static DriverListeners driverListener = new DriverListeners();
	private static Logger log = Logger.getLogger(Page.class);
	private static FileInputStream fis = null;
	private static Properties prop = null;
	private static Calendar calender = null;
	private static WebDriverWait wait = null;

	protected static void setDriver(WebDriver driver) {
		eventDriver = new EventFiringWebDriver(driver);
		eventDriver.register(driverListener);
		webDriver.set(eventDriver);

	}

	public static void setLocProperties() throws FileNotFoundException, IOException {
		fis = new FileInputStream(
				new File("E:\\SeleniumFrameworks\\DataDrivenFrameworks\\src\\main\\resources\\Locators.properties"));
		prop = new Properties();
		prop.load(fis);
	}

	protected static WebDriver getDriver() {
		return webDriver.get();
	}

	protected static void getURL(String url) {
		getDriver().get(url);
	}

	protected static void navigateToUrl(String url) {
		getDriver().navigate().to(url);
	}

	protected static void navigateForward() {
		getDriver().navigate().forward();
	}

	protected static void navigateBackward() {
		getDriver().navigate().back();
	}

	protected static WebElement getWebElement(String locator) {
		wait = new WebDriverWait(getDriver(), 20);
		WebElement element = null;
		try {
			if (locator.startsWith("xpath")) {
				element = getDriver().findElement(By.xpath(prop.getProperty(locator)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (locator.startsWith("css")) {
				element = getDriver().findElement(By.cssSelector(prop.getProperty(locator)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (locator.startsWith("id")) {
				element = getDriver().findElement(By.id(prop.getProperty(locator)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (locator.startsWith("name")) {
				element = getDriver().findElement(By.name(prop.getProperty(locator)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (locator.startsWith("linktext")) {
				element = getDriver().findElement(By.linkText(prop.getProperty(locator)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (locator.startsWith("plinktext")) {
				element = getDriver().findElement(By.partialLinkText(prop.getProperty(locator)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (locator.startsWith("tag")) {
				element = getDriver().findElement(By.tagName(prop.getProperty(locator)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (locator.startsWith("class")) {
				element = getDriver().findElement(By.className(prop.getProperty(locator)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			}
		} catch (Exception E) {
			E.getMessage();
		}
		return element;

	}

	protected static boolean isElementExists(String locator) {

		int size = 0;
		try {
			if (locator.startsWith("xpath")) {
				size = getDriver().findElements(By.xpath(prop.getProperty(locator))).size();

			} else if (locator.startsWith("css")) {
				size = getDriver().findElements(By.cssSelector(prop.getProperty(locator))).size();

			} else if (locator.startsWith("id")) {
				size = getDriver().findElements(By.id(prop.getProperty(locator))).size();

			} else if (locator.startsWith("name")) {
				size = getDriver().findElements(By.name(prop.getProperty(locator))).size();

			} else if (locator.startsWith("linktext")) {
				size = getDriver().findElements(By.linkText(prop.getProperty(locator))).size();

			} else if (locator.startsWith("plinktext")) {
				size = getDriver().findElements(By.partialLinkText(prop.getProperty(locator))).size();

			} else if (locator.startsWith("tag")) {
				size = getDriver().findElements(By.tagName(prop.getProperty(locator))).size();

			} else if (locator.startsWith("class")) {
				size = getDriver().findElements(By.className(prop.getProperty(locator))).size();

			}
		} catch (Exception E) {
			E.getMessage();
		}
		if (size != 0)
			return true;
		else
			return false;

	}

	protected static boolean isElementEnabled(String locator) {
		return getWebElement(locator).isEnabled();

	}

	protected static boolean isElementDisplayed(String locator) {
		return getWebElement(locator).isDisplayed();
	}

	protected static boolean isElementSelected(String locator) {
		return getWebElement(locator).isSelected();
	}

	protected static void setText(String locator, String text) throws IOException {

		getWebElement(locator).sendKeys(text);

	}

	protected static void clickElement(String locator) throws IOException {

		getWebElement(locator).click();

	}

	protected static void submitForm(String locator) throws IOException {

		getWebElement(locator).submit();
	}

	protected static void clearData(String locator) {
		getWebElement(locator).clear();

	}

	protected static String takeScreenShot(String ScreenShotName) {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		calender = Calendar.getInstance();
		Date date = calender.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		formattedDate = formattedDate.replace(":", "_");
		File des = new File(System.getProperty("user.dir") + "\\test-output\\ScreenShots\\" + ScreenShotName
				+ formattedDate + ".png");
		;
		try {

			FileUtils.copyFile(src, des);
			System.out.println(System.getProperty("user.dir"));
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return System.getProperty("user.dir") + "\\test-output\\ScreenShots\\" + ScreenShotName + formattedDate
				+ ".png";

	}

	protected static void tearUp() {
		getDriver().quit();
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
