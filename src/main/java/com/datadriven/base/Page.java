package com.datadriven.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.datadriven.listeners.DriverListeners;

public class Page {
	private static EventFiringWebDriver eventDriver;
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private static DriverListeners dListener = new DriverListeners();
	private static Logger log = Logger.getLogger(Page.class);
	static FileInputStream fis=null;
	  static Properties prop=null;
	
	protected static void setDriver(WebDriver driver) throws IOException {
		eventDriver = new EventFiringWebDriver(driver);
		eventDriver.register(dListener);
		webDriver.set(eventDriver);
	 fis=new FileInputStream(new File("E:\\SeleniumFrameworks\\DataDrivenFrameworks\\src\\main\\java\\com\\datadriven\\locators\\Locators.properties"));
		  prop=new Properties();
		  prop.load(fis);
	}

	protected static WebDriver getDriver() {
		return webDriver.get();
	}


	protected static WebElement getWebElement(String locator) {
		WebElement element = null;
		try {
			if(locator.startsWith("xpath")) {
				element = getDriver().findElement(By.xpath(prop.getProperty(locator)));
			} else if(locator.startsWith("css")) {
				element = getDriver().findElement(By.cssSelector(prop.getProperty(locator)));
			} else if(locator.startsWith("id")) {
				element = getDriver().findElement(By.id(prop.getProperty(locator)));
			} else if(locator.startsWith("name")) {
				element = getDriver().findElement(By.name(prop.getProperty(locator)));
			} else if(locator.startsWith("linktext")) {
				element = getDriver().findElement(By.linkText(prop.getProperty(locator)));
			} else if(locator.startsWith("plinktext")) {
				element = getDriver().findElement(By.partialLinkText(prop.getProperty(locator)));
			} else if(locator.startsWith("tag")) {
				element = getDriver().findElement(By.tagName(prop.getProperty(locator)));
			} else if(locator.startsWith("class")) {
				element = getDriver().findElement(By.className(prop.getProperty(locator)));
			}
		} catch (NoSuchElementException E) {
			log.debug("Element with " + prop.getProperty(locator) + " not found ");
		}
		return element;

	}

	protected static boolean isElementExists(String locator) {
		int size = 0;
		if(locator.startsWith("xpath")) {
			size = getDriver().findElements(By.xpath(prop.getProperty(locator))).size();
		} else if(locator.startsWith("css")) {
			size = getDriver().findElements(By.cssSelector(prop.getProperty(locator))).size();
		} else if(locator.startsWith("id")) {
			size = getDriver().findElements(By.id(prop.getProperty(locator))).size();
		} else if(locator.startsWith("name")) {
			size = getDriver().findElements(By.name(prop.getProperty(locator))).size();
		} else if(locator.startsWith("linktext")) {
			size = getDriver().findElements(By.linkText(prop.getProperty(locator))).size();
		} else if(locator.startsWith("plinktext")) {
			size = getDriver().findElements(By.partialLinkText(prop.getProperty(locator))).size();
		} else if(locator.startsWith("tag")) {
			size = getDriver().findElements(By.tagName(prop.getProperty(locator))).size();
		} else if(locator.startsWith("class")) {
			size = getDriver().findElements(By.className(prop.getProperty(locator))).size();
		}

		if (size != 0)
			return true;
		else
			return false;
	}

	protected static boolean isElementEnabled(String locator) {
		Boolean flag = false;
		try {
			if(locator.startsWith("xpath")) {
				flag = getDriver().findElement(By.xpath(prop.getProperty(locator))).isEnabled();
			} else if(locator.startsWith("css")) {
				flag = getDriver().findElement(By.cssSelector(prop.getProperty(locator))).isEnabled();
			} else if(locator.startsWith("id")) {
				flag = getDriver().findElement(By.id(prop.getProperty(locator))).isEnabled();
			} else if(locator.startsWith("name")) {
				flag = getDriver().findElement(By.name(prop.getProperty(locator))).isEnabled();
			} else if(locator.startsWith("linktext")) {
				flag = getDriver().findElement(By.linkText(prop.getProperty(locator))).isEnabled();
			} else if(locator.startsWith("plinktext")) {
				flag = getDriver().findElement(By.partialLinkText(prop.getProperty(locator))).isEnabled();
			} else if(locator.startsWith("tag")) {
				flag = getDriver().findElement(By.tagName(prop.getProperty(locator))).isEnabled();
			} else if(locator.startsWith("class")) {
				flag = getDriver().findElement(By.className(prop.getProperty(locator))).isEnabled();
			}
		} catch (NoSuchElementException E) {
			log.debug("Element with " + prop.getProperty(locator) + " not found ");
		}
		return flag;
	}

	protected static boolean isElementDisplayed(String locator) {
		Boolean flag = false;
		try {
			if(locator.startsWith("xpath")) {
				flag = getDriver().findElement(By.xpath(prop.getProperty(locator))).isDisplayed();
			} else if(locator.startsWith("css")) {
				flag = getDriver().findElement(By.cssSelector(prop.getProperty(locator))).isDisplayed();
			} else if(locator.startsWith("id")) {
				flag = getDriver().findElement(By.id(prop.getProperty(locator))).isDisplayed();
			} else if(locator.startsWith("name")) {
				flag = getDriver().findElement(By.name(prop.getProperty(locator))).isDisplayed();
			} else if(locator.startsWith("linktext")) {
				flag = getDriver().findElement(By.linkText(prop.getProperty(locator))).isDisplayed();
			} else if(locator.startsWith("plinktext")) {
				flag = getDriver().findElement(By.partialLinkText(prop.getProperty(locator))).isDisplayed();
			} else if(locator.startsWith("tag")) {
				flag = getDriver().findElement(By.tagName(prop.getProperty(locator))).isDisplayed();
			} else if(locator.startsWith("class")) {
				flag = getDriver().findElement(By.className(prop.getProperty(locator))).isDisplayed();
			}
		} catch (NoSuchElementException E) {
			log.debug("Element with " + prop.getProperty(locator) + " not found ");
		}
		return flag;
	}

	protected static boolean isElementSelected(String locator) {
		Boolean flag = false;
		try {
			if(locator.startsWith("xpath")) {
				flag = getDriver().findElement(By.xpath(prop.getProperty(locator))).isSelected();
			} else if(locator.startsWith("css")) {
				flag = getDriver().findElement(By.cssSelector(prop.getProperty(locator))).isSelected();
			} else if(locator.startsWith("id")) {
				flag = getDriver().findElement(By.id(prop.getProperty(locator))).isSelected();
			} else if(locator.startsWith("name")) {
				flag = getDriver().findElement(By.name(prop.getProperty(locator))).isSelected();
			} else if(locator.startsWith("linktext")) {
				flag = getDriver().findElement(By.linkText(prop.getProperty(locator))).isSelected();
			} else if(locator.startsWith("plinktext")) {
				flag = getDriver().findElement(By.partialLinkText(prop.getProperty(locator))).isSelected();
			} else if(locator.startsWith("tag")) {
				flag = getDriver().findElement(By.tagName(prop.getProperty(locator))).isSelected();
			} else if(locator.startsWith("class")) {
				flag = getDriver().findElement(By.className(prop.getProperty(locator))).isSelected();
			}
		} catch (NoSuchElementException E) {
			log.debug("Element with " + prop.getProperty(locator) + " not found ");
		}
		return flag;
	}

	protected static void setText(String locator, String text) throws IOException {
	
		try {
			if(locator.startsWith("xpath")) {
				getDriver().findElement(By.xpath(prop.getProperty(locator))).sendKeys(text);
			} else if(locator.startsWith("css")) {
				getDriver().findElement(By.cssSelector(prop.getProperty(locator))).sendKeys(text);
			} else if(locator.startsWith("id")) {
				getDriver().findElement(By.id(prop.getProperty(locator))).sendKeys(text);
			} else if(locator.startsWith("name")) {
				getDriver().findElement(By.name(prop.getProperty(locator))).sendKeys(text);
			} else if(locator.startsWith("linktext")) {
				getDriver().findElement(By.linkText(prop.getProperty(locator))).sendKeys(text);
			} else if(locator.startsWith("plinktext")) {
				getDriver().findElement(By.partialLinkText(prop.getProperty(locator))).sendKeys(text);
			} else if(locator.startsWith("tag")) {
				getDriver().findElement(By.tagName(prop.getProperty(locator))).sendKeys(text);
			} else if(locator.startsWith("class")) {
				getDriver().findElement(By.className(prop.getProperty(locator))).sendKeys(text);
			}
		} catch (NoSuchElementException E) {
			log.debug("Element with " + prop.getProperty(locator) + " not found ");
		} catch (IllegalArgumentException E) {
			log.debug("Null is passed in send keys that is not allowed");
		}

	}

	protected static void clickElement(String locator) throws IOException {
	
		try {
			if(locator.startsWith("xpath")) {
				getDriver().findElement(By.xpath(prop.getProperty(locator))).click();
			} else if(locator.startsWith("css")) {
				getDriver().findElement(By.cssSelector(prop.getProperty(locator))).click();
			} else if(locator.startsWith("id")) {
				getDriver().findElement(By.id(prop.getProperty(locator))).click();
			} else if(locator.startsWith("name")) {
				getDriver().findElement(By.name(prop.getProperty(locator))).click();
			} else if(locator.startsWith("linktext")) {
				getDriver().findElement(By.linkText(prop.getProperty(locator))).click();
			} else if(locator.startsWith("plinktext")) {
				getDriver().findElement(By.partialLinkText(prop.getProperty(locator))).click();
			} else if(locator.startsWith("tag")) {
				getDriver().findElement(By.tagName(prop.getProperty(locator))).click();
			} else if(locator.startsWith("class")) {
				getDriver().findElement(By.className(prop.getProperty(locator))).click();
			}
		} catch (StaleElementReferenceException E) {
			log.debug("Element with " + prop.getProperty(locator) + " is no longer avaliable on the page ");
		} catch (NoSuchElementException E) {
			log.debug("Element with " + prop.getProperty(locator) + " not found ");
		}

	}

	protected static void submitForm(String locator) throws IOException {
		
		try {
			if(locator.startsWith("xpath")) {
				getDriver().findElement(By.xpath(prop.getProperty(locator))).submit();
			} else if(locator.startsWith("css")) {
				getDriver().findElement(By.cssSelector(prop.getProperty(locator))).submit();
			} else if(locator.startsWith("id")) {
				getDriver().findElement(By.id(prop.getProperty(locator))).submit();
			} else if(locator.startsWith("name")) {
				getDriver().findElement(By.name(prop.getProperty(locator))).submit();
			} else if(locator.startsWith("linktext")) {
				getDriver().findElement(By.linkText(prop.getProperty(locator))).submit();
			} else if(locator.startsWith("plinktext")) {
				getDriver().findElement(By.partialLinkText(prop.getProperty(locator))).submit();
			} else if(locator.startsWith("tag")) {
				getDriver().findElement(By.tagName(prop.getProperty(locator))).submit();
			} else if(locator.startsWith("class")) {
				getDriver().findElement(By.className(prop.getProperty(prop.getProperty(locator)))).submit();
			}
		} catch (NoSuchElementException E) {
			log.debug("Element with " + prop.getProperty(locator) + " not found ");
		}
	}

	protected static void clearData(String locator) {
		try {
			if(locator.startsWith("xpath")) {
				getDriver().findElement(By.xpath(prop.getProperty(locator))).clear();
			} else if(locator.startsWith("css")) {
				getDriver().findElement(By.cssSelector(prop.getProperty(locator))).clear();
			} else if(locator.startsWith("id")) {
				getDriver().findElement(By.id(prop.getProperty(locator))).clear();
			} else if(locator.startsWith("name")) {
				getDriver().findElement(By.name(prop.getProperty(locator))).clear();
			} else if(locator.startsWith("linktext")) {
				getDriver().findElement(By.linkText(prop.getProperty(locator))).clear();
			} else if(locator.startsWith("plinktext")) {
				getDriver().findElement(By.partialLinkText(prop.getProperty(locator))).clear();
			} else if(locator.startsWith("tag")) {
				getDriver().findElement(By.tagName(prop.getProperty(locator))).clear();
			} else if(locator.startsWith("class")) {
				getDriver().findElement(By.className(prop.getProperty(locator))).clear();
			}
		} catch (NoSuchElementException E) {
			log.debug("Element with " + prop.getProperty(locator) + " not found ");
		}

	}

}
