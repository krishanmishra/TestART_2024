package com.art.qa.flux;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.art.qa.Base.BaseTest;
import com.art.qa.constants.Constants;

public class FluxRule extends BaseTest {

	@Test
	public void testRunAllRules() throws Exception {

		homePage=loginPage.loginUser();
		createFluxPage=homePage.navigateToCreateFluxRule();
		String alertText=createFluxPage.clickFluxRule("Run All Rules");
		Assert.assertEquals(alertText,Constants.RULE_BTN_VALIDATION_POP_UP);
		loginPage=homePage.logoutUser();
		
	}
	
	@Test
	public void testRunRule() throws Exception {

		homePage=loginPage.loginUser();
		createFluxPage=homePage.navigateToCreateFluxRule();
		String alertText=createFluxPage.clickFluxRule("Run Rule #");
		Assert.assertEquals("Rules can only run in Open, In Progress, or Opening In Progress periods.", alertText);
		loginPage=homePage.logoutUser();
		
	}

}
