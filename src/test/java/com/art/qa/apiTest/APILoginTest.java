package com.art.qa.apiTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.art.qa.Base.BaseTest;
import com.art.qa.constants.Constants;

public class APILoginTest extends BaseTest {

	@Test
	public void validAPILoginTest() throws Exception {

		homePage=loginPage.loginUser();
		Assert.assertTrue(getDriver().getCurrentUrl().contains(Constants.Home_PAGE_URL_FRACTION));
		loginPage=homePage.logoutUser();
		Assert.assertTrue(getDriver().getTitle().contains(Constants.LOGIN_PAGE_TITLE));
	}
	
	
	
	
	//need to configure log, reports
	//need to configure jenkins
	//need to create multiple pom file
	//need create group for sanity ,regression and smoke for testng
	//need to create separate package for api module
	
	
	
	
}
