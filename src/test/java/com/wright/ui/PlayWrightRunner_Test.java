package com.wright.ui;

import org.testng.annotations.Test;

import com.wright.ui.pages.BaseSteps;

public class PlayWrightRunner_Test extends BaseSteps {
	
	@Test
    public void testGetStarted() throws InterruptedException{
    		
    	//GettingStartedPage gPage = new GettingStartedPage(page); - This can be initantiated in BaseSteps class
		System.out.println("Test Started");
    	gettingStartedPage.clickGetStarted();
    	gettingStartedPage.doSearch();
    	
    	multiWindowOrTabPage.openNewTab();
	}
	
}