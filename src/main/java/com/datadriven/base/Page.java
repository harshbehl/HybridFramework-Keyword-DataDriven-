package com.datadriven.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.datadriven.listeners.DriverListeners;
import com.datadriven.reporting.ExtentReporting;

public class Page {
	private EventFiringWebDriver eventDriver;
	private WebDriver webDriver;
	private DriverListeners driverListener;
	private static Logger log = Logger.getLogger(Page.class);
	private FileInputStream fis;
	private Calendar calender;
	private WebDriverWait wait;
	private Hashtable<String, String> dataTable;
	private String dataKey;
	private String objectKey;
	private Properties envProp;
	private String proceedOnFail;



	/**** Getters and Setters ******/
	public String getProceedOnFail() {
		return proceedOnFail;
	}

	public void setProceedOnFail(String proceedOnFail) {
		this.proceedOnFail = proceedOnFail;
	}  
	
	public Properties getEnvProp() {
		return envProp;
	}

	public void setEnvProp(Properties envProp) {
		if(this.envProp==null)
		this.envProp = envProp;
	}

	public Properties getLocProp() {
		return locProp;
	}

	public void setLocProp(Properties locProp) {
		if(this.locProp==null)
		this.locProp = locProp;
	}

	private Properties locProp;

	public String getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	

	public FileInputStream getFis() {
		return fis;
	}

	public void setFis(FileInputStream fis) {
		this.fis = fis;
	}

	

	public Hashtable<String, String> getDataTable() {
		return dataTable;
	}

	public void setDataTable(Hashtable<String, String> dataTable) {
		this.dataTable = dataTable;
	}

	/************* Keywords/Actions ***************/

	public void OpenBrowser() {
		try {
			driverListener = new DriverListeners();
			if (dataTable.get(getDataKey()).equalsIgnoreCase("Mozilla")) {
				System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_PATH);
				webDriver = new FirefoxDriver();
				eventDriver = new EventFiringWebDriver(webDriver);
				eventDriver.register(driverListener);
			} else if (dataTable.get(getDataKey()).equalsIgnoreCase("Chrome")) {
				webDriver = new ChromeDriver();
				eventDriver = new EventFiringWebDriver(webDriver);
				eventDriver.register(driverListener);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}





	public void getURL() {
		webDriver.get(envProp.getProperty(getObjectKey()));
	}

	public void navigateToUrl() {
		webDriver.navigate().to(envProp.getProperty(getObjectKey()));
	}

	public void navigateForward() {
		webDriver.navigate().forward();
	}

	public void navigateBackward() {
		webDriver.navigate().back();
	}

	public WebElement getWebElement() {
		wait = new WebDriverWait(webDriver, Constants.WEBDRIVER_EXPLICIT_WAIT);
		WebElement element = null;
		try {
			if (objectKey.startsWith("xpath")) {
				element = webDriver.findElement(By.xpath(locProp.getProperty(objectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (objectKey.startsWith("css")) {
				element = webDriver.findElement(By.cssSelector(locProp.getProperty(objectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (objectKey.startsWith("id")) {
				element = webDriver.findElement(By.id(locProp.getProperty(objectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (objectKey.startsWith("name")) {
				element = webDriver.findElement(By.name(locProp.getProperty(objectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (objectKey.startsWith("linktext")) {
				element = webDriver.findElement(By.linkText(locProp.getProperty(objectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (objectKey.startsWith("plinktext")) {
				element = webDriver.findElement(By.partialLinkText(locProp.getProperty(objectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (objectKey.startsWith("tag")) {
				element = webDriver.findElement(By.tagName(locProp.getProperty(objectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			} else if (objectKey.startsWith("class")) {
				element = webDriver.findElement(By.className(locProp.getProperty(objectKey)));
				wait.until(ExpectedConditions.visibilityOfAllElements(element));
			}
		} catch (Exception E) {
			E.getMessage();
		}
		return element;

	}

	public boolean isElementExists(String locator) {

		int size = 0;
		try {
			if (objectKey.startsWith("xpath")) {
				size = webDriver.findElements(By.xpath(locProp.getProperty(objectKey))).size();

			} else if (objectKey.startsWith("css")) {
				size = webDriver.findElements(By.cssSelector(locProp.getProperty(objectKey))).size();

			} else if (objectKey.startsWith("id")) {
				size = webDriver.findElements(By.id(locProp.getProperty(objectKey))).size();

			} else if (objectKey.startsWith("name")) {
				size = webDriver.findElements(By.name(locProp.getProperty(objectKey))).size();

			} else if (objectKey.startsWith("linktext")) {
				size = webDriver.findElements(By.linkText(locProp.getProperty(objectKey))).size();

			} else if (objectKey.startsWith("plinktext")) {
				size = webDriver.findElements(By.partialLinkText(locProp.getProperty(objectKey))).size();

			} else if (objectKey.startsWith("tag")) {
				size = webDriver.findElements(By.tagName(locProp.getProperty(objectKey))).size();

			} else if (objectKey.startsWith("class")) {
				size = webDriver.findElements(By.className(locProp.getProperty(objectKey))).size();

			}
		} catch (Exception e) {
			e.getMessage();
		}
		if (size != 0)
			return true;
		else
			return false;

	}

	public boolean isElementEnabled() {
		return getWebElement().isEnabled();
	}

	public boolean isElementDisplayed() {
		return getWebElement().isDisplayed();
	}

	public boolean isElementSelected() {
		return getWebElement().isSelected();
	}

	public void setText() throws IOException {

		getWebElement().sendKeys(dataTable.get(getDataKey()));
		ExtentReporting.getTest().log(Status.PASS, "Entered text "+dataTable.get(dataKey)+" on the field " +objectKey);

	}

	public void clickElement() throws IOException {

		getWebElement().click();
		ExtentReporting.getTest().log(Status.PASS, "Succesfully Clicked on " +objectKey);
	}

	public void submitForm() throws IOException {

		getWebElement().submit();
	}

	public void clearData() {
		getWebElement().clear();

	}

	public String takeScreenShot(String ScreenShotName) {
		File src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		calender = Calendar.getInstance();
		Date date = calender.getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String formattedDate = dateFormat.format(date);
		formattedDate = formattedDate.replace(":", "_");
		File des = new File(Constants.SCREENSHOTS_PATH + ScreenShotName + formattedDate + ".png");
		;
		try {

			FileUtils.copyFile(src, des);
			System.out.println(System.getProperty("user.dir"));
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return Constants.SCREENSHOTS_PATH + ScreenShotName + formattedDate + ".png";

	}

	public void tearUp() {
		if(webDriver!=null)
		webDriver.quit();
		
	}

}
