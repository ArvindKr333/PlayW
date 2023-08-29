package com.wright.ui.pages;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.MouseButton;
import com.wright.ui.utils.Utility;

public class MultiWindowOrTabPage {
	
	private Page page;
	private static final String SYSTEM_REQUIREMENT_LINK = "//a[text()='System requirements']";
	
	public MultiWindowOrTabPage(Page page) {
		this.page = page;
	}
	
	public void openNewTab() throws InterruptedException {
		
		Thread.sleep(2000);
		page.locator(SYSTEM_REQUIREMENT_LINK).click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
		 // Opens a new tab
		String href = page.locator(SYSTEM_REQUIREMENT_LINK).getAttribute("href");
		
		
		Object obj = page.evaluate("() => Array.from(document.links).map(item => item.href)");
		@SuppressWarnings("unchecked")
		ArrayList<String> list = (ArrayList<String>)obj;
		
		/*
		 * for(String sys : list) { if(sys.contains(href)) href = sys;
		 * 
		 * }
		 */
		
		//String h = page.querySelector("a[href='#system-requirements']").innerHTML();
		
		String jsScript = "() => Array.from(document.links).map(item => item.href).find((item) => { \r\n"
				+ "    if(item.includes(" + "\"" + href +"\"" + "))\r\n"
				+ "       { \r\n"
				+ "        return item; \r\n"
				+ "       }\r\n"
				+ "    }\r\n"
				+ "    );";
		
		Object hrefSys = page.evaluate(jsScript);
		
		Page newPage = page.context().newPage();
		newPage.navigate(hrefSys.toString());
		
		List<Page> pages = newPage.context().pages();
		System.out.println("There are " + pages.size() +  " tabs.");
		
		Thread.sleep(2000);
		Utility.createScreenshot(newPage);
		//newPage.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots\\tab new.jpg")));
			
		Thread.sleep(2000);
	} 

}
