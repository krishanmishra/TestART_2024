package com.art.qa.pageHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.art.qa.Base.BasePage;
import com.art.qa.Base.Utilities;
import com.art.qa.constants.Constants;

public class HomePage extends BasePage {

	private By homepage_username = By.id("dropdownMenuLink");
	private By logout_loc = By.xpath("//a[contains(text(),'Logout')]");
	private By link_text = By.linkText("Forgot Your Password");
	private By rec_group_list_loc = By.id("recgroup-list");
	private By rec_period_list_loc=By.id("ReconcilationPeriodId");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public LoginPage logoutUser() {
		waitForPageLoaded();
		waitForElementVisible(getElement(homepage_username));
		waitAndClick(getElement(homepage_username));
		waitForElement(getElement(logout_loc));
		clickOn(getElement(logout_loc));
		waitForPageLoaded();
		waitForElement(getElement(link_text));
		return new LoginPage(driver);
	}

	public AccountViewerPage navigateToAccountViewer() {
		WebElement title = navigateToScreen(Constants.HOVER_OVER_ACCOUNT_IMG, Constants.CLICK_ACCOUNT_VIEWER);
		verifyElementText(title, Constants.ACCOUNT_VIEWER_PAGE_TITLE);
		Assert.assertTrue(verifyURL(Constants.ACCOUNT_VIEWER_FRACTION));
		return new AccountViewerPage(driver);

	}

	public CreateFluxPage navigateToCreateFluxRule() {
		WebElement title = navigateToScreen(Constants.HOVER_OVER_RESERACH_IMG, Constants.CLICK_CREATE_FLUX_RULE);
		verifyElementText(title, Constants.CREATE_FLUX_RULE_PAGE_TITLE);
		Assert.assertTrue(verifyURL(Constants.CREATE_FLUX_RULE__URL_FRACTION));
		return new CreateFluxPage(driver);

	}

	public HomePage selectRecGroup(String recGroup) throws Exception {

		selectDropDownByValue(getElement(rec_group_list_loc), recGroup);
		return new HomePage(driver);
	}
	
	public HomePage selectRecGroup() throws Exception {

		String recGroup = System.getProperty("recgroup") != null ? System.getProperty("recgroup")
			: Utilities.readPropertiesFile("recgroup");
		selectDropDownByValue(getElement(rec_group_list_loc), recGroup);

		return new HomePage(driver);
	}

	public HomePage selectRecPeriod() throws Exception {

		String recperiod = System.getProperty("recperiod") != null ? System.getProperty("recperiod")
				: Utilities.readPropertiesFile("recperiod");				
		selectDropDownByValue(getElement(rec_period_list_loc),recperiod);

		return new HomePage(driver);
	}

	

}
