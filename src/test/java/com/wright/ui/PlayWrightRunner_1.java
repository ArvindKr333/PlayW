package com.wright.ui;

import org.testng.annotations.Test;

import com.wright.ui.pages.BaseSteps;

public class PlayWrightRunner_1 extends BaseSteps {
	
	@Test
    public void testGetStarted() throws InterruptedException{
    		
    	//GettingStartedPage gPage = new GettingStartedPage(page); - This can be initantiated in BaseSteps class
		
    	gettingStartedPage.clickGetStarted();
    	gettingStartedPage.doSearch();
    	
    	multiWindowOrTabPage.openNewTab();
	}
	
}