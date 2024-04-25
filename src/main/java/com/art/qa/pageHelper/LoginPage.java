package com.art.qa.pageHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.art.qa.Base.BasePage;
import com.art.qa.Base.Utilities;


public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);

	}
	
	private By username_loc = By.xpath("//input[@name='Email']");
	private By password_loc = By.xpath("//input[@name='Password']");
	private By loginBtn_loc = By.xpath("//input[@value='Login']");
	private By widgets_loc=By.xpath("//*[text() = ' Reconciliation Status ']");
	
	public void enterUserName(String username) {

		inputText(getElement(username_loc), username);
	}
	
	public void enterPassword(String password) {
		
		inputText(getElement(password_loc), password);			
	}

	public void clickLogin() {
		
		clickOn(getElement(loginBtn_loc));
		
	}
	
	public HomePage loginUser() throws Exception {
		
		String userName = System.getProperty("username") != null ? System.getProperty("username")
				: Utilities.readPropertiesFile("username");
		
		
		String password = System.getProperty("password") != null ? System.getProperty("password")
				: Utilities.readPropertiesFile("password");
		//String usernames=Utilities.readPropertiesFile("usernames");
		//String password=Utilities.readPropertiesFile("password");

		enterUserName(userName);
		enterPassword(password);
		clickLogin();
		waitForPageLoaded();
		waitForElement(getElement(widgets_loc));
		return new HomePage(driver);
	}
	
	

}
