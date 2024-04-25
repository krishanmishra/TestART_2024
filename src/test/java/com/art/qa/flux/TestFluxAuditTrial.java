package com.art.qa.flux;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.art.qa.Base.BaseTest;
import com.art.qa.constants.Constants;

public class TestFluxAuditTrial extends BaseTest {

	String rule_id;
	boolean flag;
	
	@Test(description="Created Flux Rule and Test Flux rule exist into Audit Trail window into Rec group")
	public void testFluxAuditTrial() throws Exception {

		homePage=loginPage.loginUser();
		homePage=homePage.selectRecGroup();
		homePage=homePage.selectRecPeriod();
		createFluxPage=homePage.navigateToCreateFluxRule(); 
		rule_id=createFluxPage.createFluxRule();		
		flag=createFluxPage.auditTrailWindow(rule_id);
		Assert.assertTrue(flag,"Rules has not been existing there");
		loginPage=homePage.logoutUser();	
	}
	
	@Test(description="ART-2030:Flux/Variance Audit Trail is not Rec Group Specific",dependsOnMethods="testFluxAuditTrial")
	public void testRecGroupSpecificAuditTrial() throws Exception {
		
		homePage=loginPage.loginUser();
		homePage=homePage.selectRecGroup(Constants.RecGroup);
		homePage=homePage.selectRecPeriod();
		createFluxPage=homePage.navigateToCreateFluxRule();
		System.out.println(rule_id);
		flag=createFluxPage.auditTrailWindow(rule_id);
		Assert.assertEquals(flag, false,"Rules has been existing there");
		loginPage=homePage.logoutUser();
	}

}
