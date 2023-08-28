package com.wright.ui.pages;

import java.nio.file.Paths;

import org.testng.Assert;

import com.microsoft.playwright.Page;
import com.wright.ui.utils.Utility;

public class GettingStartedPage {

	private Page page;
	private static final String GET_STARTED_BUTTON = "//div/a[contains(text(), 'Get started')]";

	public GettingStartedPage(Page page) {
		this.page = page;
	}

	public void clickGetStarted() {

		Utility.createScreenshot(page);
		//page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshots\\test.jpg")));

		Assert.assertTrue(page.locator("//div/a[contains(text(), 'Get started')]").isVisible());
		page.locator(GET_STARTED_BUTTON).click();

		page.locator("//a[contains(text(), 'Getting Started')]").click();

		String pageTitle = page.title();
		System.out.println(pageTitle);

		// page.evaluateHandle("() => window.scrollBy(0, 700)");

		try {
			Thread.sleep(4000);

			// scroll via javascript
			page.evaluateHandle("() => window.scrollBy(0, 1800)");

			Thread.sleep(1000);

			// mouse scroll via playwright
			page.mouse().wheel(0, 600);

			page.evaluate("window.scrollBy(0, 600)");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void doSearch() {
		try {
			Thread.sleep(1000);
			page.locator("//span[@class = 'DocSearch-Button-Container']").click();
			page.fill("//input[@id = 'docsearch-input']", "make");

			Thread.sleep(1000);
			page.keyboard().press("Escape");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
