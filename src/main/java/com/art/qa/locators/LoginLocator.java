package com.art.qa.locators;

import org.openqa.selenium.By;

public class LoginLocator {

	public static final By username_loc = By.xpath("//input[@name='Email']");
	public static final By password_loc = By.xpath("//input[@name='Password']");
	public static final By loginBtn_loc = By.xpath("//input[@value='Login']");
	public static final By logout_loc = By.xpath("//a[contains(text(),'Logout')]");
	public static final By username_home = By.xpath("//div//a[@id='dropdownMenuLink']");
	public static final By rec_widget_loc = By.cssSelector("div#ReconciliationStatus");
	public static final By close_window=By.className("rwCloseButton");
	
	
	

}
