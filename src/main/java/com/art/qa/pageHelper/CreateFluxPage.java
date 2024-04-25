package com.art.qa.pageHelper;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.art.qa.Base.BasePage;

public class CreateFluxPage extends BasePage {

	public CreateFluxPage(WebDriver driver) {
		super(driver);

	}

	private By flux_types = By.cssSelector("#ctl00_ContentPlaceHolder1_ddlFluxRuleType");
	private By select_rule = By.cssSelector("#ctl00_ContentPlaceHolder1_ddlFluxRule");
	private By enter_rule = By.cssSelector("#ctl00_ContentPlaceHolder1_txtRule_txtRule_TextBox");
	private By flux_threshold = By.cssSelector("#ctl00_ContentPlaceHolder1_txtFluxThresholdValue");
	private By flux_Threshold_CurrencyType = By.cssSelector("#ctl00_ContentPlaceHolder1_ddlCurrencyType");
	private By saveRule = By.cssSelector("#ctl00_ContentPlaceHolder1_btnSaveRule");
	private By assign_rule_to_all_Accounts = By
			.cssSelector("#ctl00_ContentPlaceHolder1_chkAssignRuleToAllAccounts_ctl00");
	private By flux_number = By.id("ctl00_ContentPlaceHolder1_lblFluxRuleNumberValue");
	private By confirmation_msg = By.id("ctl00_lblConfirmationMessage");
	private By trail_link = By.id("ctl00_ContentPlaceHolder1_hlFluxRuleAuditTrail");
	private By flux_id_audit_trail = By.xpath("//span[contains(@id,'FluxRuleNumber')]");

	public String clickFluxRule(String text) {
		try {
			clickOn(text);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			System.out.println("No alert was present within the specified time.");
		}

		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();

		return alertText;

	}

	public String createFluxRule() {

		selectDropDownByValue(getElement(flux_types), "BS Flux");
		inputText(getElement(enter_rule), "Test1");
		inputText(getElement(flux_threshold), "100");
		selectDropDownByValue(getElement(flux_Threshold_CurrencyType), "RCCY");

		clickOn("Save Rule");

		String message = getElement(confirmation_msg).getText();
		System.out.println(message);

		Assert.assertEquals("Flux Rule Created", message);

		String rule_id = getElement(flux_number).getText();
		System.out.println(rule_id);
		return rule_id;
	}

	public Boolean auditTrailWindow(String rule_id) {
		scrollToElement(getElement(trail_link));
		clickOn(getElement(trail_link));
		waitForPageLoaded();
		List<WebElement> elments = driver.findElements(flux_id_audit_trail);
		List<String> rules_id = switchToChildWindow(elments);

		for (String rule : rules_id) {
			if (rule.equals(rule_id)) {
				return true;
			}
		}

		return false;

	}

}
