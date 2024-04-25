package com.art.qa.Base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.art.qa.constants.Constants;
import com.art.qa.locators.LoginLocator;

public class BasePage {

	protected WebDriver driver;

	public BasePage(WebDriver driver) {

		this.driver = driver;
	}
	/*
	 * 
	 * 
	 */

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	/**
	 * 
	 * 
	 */
	public WebElement getWebElementById(String text) {
		return driver.findElement(By.xpath("//span[contains(@id,'" + text + "')]"));
	}

	public WebElement getWebElementByClass(String text) {
		return driver.findElement(By.xpath("//span[contains(@class,'" + text + "')]"));
	}

	/**
	 * Providing inputs to the given location
	 * 
	 * @param element
	 * @param text
	 */
	public void inputText(WebElement element, String text) {
		if (element.getAttribute("value").equals("")) {
			waitForElementVisible(element);
			element.sendKeys(text);
		}

	}

	/**
	 * Wait for the element to be visible
	 * 
	 * @param element
	 */
	public void waitForElementVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_ELEMENT_TIME_OUT));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			// logger.info(element.toString() + " is not present on page");
		}
	}

	/**
	 * Click on an element
	 * 
	 * @param element
	 */
	public void clickOn(WebElement element) {
		element.click();
	}

	/*
	 * 
	 * 
	 * click on button
	 */
	public void clickOn(String text) {
		WebElement el = getElement(By.xpath("//input[@value='" + text + "']"));
		if (el.isEnabled()) {
			el.click();
		} else {
			System.out.println("button is disabled");
		}

	}

	/**
	 * wait for Page Loaded
	 * 
	 * @param element
	 */

	public void waitForPageLoaded() {
		try {
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_ELEMENT_TIME_OUT));
			wait.until(expectation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wait for an element to present
	 * 
	 * @param element
	 */
	public void waitForElement(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_ELEMENT_TIME_OUT));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			// logger.info(element.toString() + " is not present on page or not clickable");
		}

	}

	/**
	 * Click action performed and then wait
	 * 
	 * @param element
	 */
	public void waitAndClick(WebElement element) {
		waitForElement(element);
		element.click();
		waitForPageLoaded();
	}

	/**
	 * navigate to screen
	 * 
	 * @param element
	 */

	public WebElement navigateToScreen(String imagename, String pagename) {

		String imageName = imagename + ".png";
		String pageName = pagename + ".aspx";

		WebElement menus = driver.findElement(By.xpath("//div/a/img[contains(@src,'" + imageName + "')]"));
		try {
			WebElement submenus = driver.findElement(By.xpath("//div/a[contains(@href,'" + pageName + "')]"));
			hoverOverElementAndClick(menus, submenus);
			return getWebElementById(Constants.PAGE_TITLE);
		} catch (Exception e) {
			WebElement submenu = driver.findElement(By.xpath("//div/a[contains(@href,'" + pagename + "')]"));
			waitForElement(submenu);
			hoverOverElementAndClick(menus, submenu);
			return getWebElementByClass(Constants.TITLE);
		}

	}

	/**
	 * 
	 * 
	 * 
	 */
	public WebDriver hoverOverElementAndClick(WebElement toBeHovered, WebElement toBeClicked) {
		waitForPageLoaded();
		Actions builder = new Actions(driver);
		Action dd = builder.moveToElement(toBeHovered).build();
		dd.perform();
		waitForElementPresent(toBeClicked, Constants.DEFAULT_ELEMENT_TIME_OUT);
		waitForElementVisible(toBeClicked);
		waitAndClick(toBeClicked);
		return driver;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 */
	public WebElement waitForElementPresent(WebElement webElement, int timeOutInSeconds) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
			element = wait.until(ExpectedConditions.visibilityOf(webElement));
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 
	 * 
	 *
	 */
	public void verifyElementText(WebElement element, String text) {
		_waitForJStoLoad();
		Assert.assertTrue(isElementPresent(element), element.toString() + " is not present");
		Assert.assertEquals(element.getText(), text);
	}

	/*
	 * 
	 * 
	 * 
	 */
	/**
	 * Wait for page using java script
	 * 
	 * @return
	 */
	public boolean _waitForJStoLoad() {
		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		/**
		 * wait for JavaScript to load
		 */
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				Object rsltJs = ((JavascriptExecutor) driver).executeScript("return document.readyState");
				if (rsltJs == null) {
					rsltJs = "";
				}
				return rsltJs.toString().equals("complete") || rsltJs.toString().equals("loaded");
			}
		};

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 */
	public Boolean isElementPresent(WebElement element) {
		try {
			_waitForJStoLoad();
			waitForElementVisible(element);
			scrollToElement(element);
			element.isDisplayed();
			return true;
		} catch (Exception ex) {
		}
		return false;
	}

	/**
	 * scroll to element
	 * 
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}

	/*
	 * 
	 * @param url
	 * 
	 * @return
	 */
	public boolean verifyURL(String url) {
		boolean value = false;
		String currentUrl = driver.getCurrentUrl();
		// logger.info("Current url: " + currentUrl);
		if (currentUrl.contains(url))
			return true;
		else
			return value;
	}

	/**
	 * Select element by value
	 * 
	 * @param element
	 * @param targetValue
	 */
	public void selectDropDownByValue(WebElement element, String targetValue) {
		waitForElementVisible(element);
		new Select(element).selectByVisibleText(targetValue);
	}

	/*	  
	 * 
	 */

	public List<String> switchToChildWindow(List<WebElement> elements) {
		
		List<String> ruleIDs=new ArrayList<String>();
		String mainWindowHandle = driver.getWindowHandle();
		// Get all window handles
		Set<String> allWindowHandles = driver.getWindowHandles();
		// Switch to new window
		for (String handle : allWindowHandles) {
			System.out.println(driver.getTitle());
			if (!handle.equals(mainWindowHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		for (WebElement el : elements) {
			if(el.getText()!=null && el.getText().isEmpty()!=true)
			System.out.println(el.getText());
			ruleIDs.add(el.getText());
		}

		clickOn(getElement(LoginLocator.close_window));
		
		// Switch back to the main window
		driver.switchTo().window(mainWindowHandle);
		return ruleIDs;

	}

	/**
	 * switchTo default main Content
	 */
	public void switchToMainWindow() {
		_waitForJStoLoad();
		driver.switchTo().defaultContent();
	}

}
