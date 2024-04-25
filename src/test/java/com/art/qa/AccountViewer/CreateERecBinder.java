package com.art.qa.AccountViewer;

import org.testng.annotations.Test;

import com.art.qa.Base.BaseTest;

public class CreateERecBinder extends BaseTest {
	
	@Test
	public void check_ERecBinder() throws Exception {
		
		homePage=loginPage.loginUser();		
		accountViewerPage=homePage.navigateToAccountViewer();		
		loginPage=homePage.logoutUser();
		
	}
	

}
