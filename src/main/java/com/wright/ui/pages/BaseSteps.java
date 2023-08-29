package com.wright.ui.pages;

import java.lang.reflect.Field;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.wright.ui.annotations.PlaywrightPage;

public class BaseSteps {
	
	protected Page page;

	protected static Playwright playwright;

	protected Browser browser;

	protected BrowserContext browserContext;
	
	@PlaywrightPage
	protected GettingStartedPage gettingStartedPage;
	
	@PlaywrightPage
	protected MultiWindowOrTabPage multiWindowOrTabPage;
	
	@BeforeSuite
	public static void init() {
		playwright = Playwright.create();
	}

	@BeforeClass
	public void setUp() {

		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(List.of("--start-maximized")));
		// .setChannel("msedge")
		browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
		page = browserContext.newPage();

		page.navigate("https://playwright.dev/");

		// gettingStartedPage = new GettingStartedPage(page);

		initPage(this, page);

	}
	
	@AfterClass
	public void tearDown() {

		browserContext.close();
		browser.close();

	}
	
	private void initPage(Object object, Page page) {

		Class<?> clazz = object.getClass().getSuperclass();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(PlaywrightPage.class)) {
				Class<?>[] type = { Page.class };
				try {
					field.set(this, field.getType().getConstructor(type).newInstance(page));
				} catch (IllegalArgumentException | IllegalAccessException | InstantiationException
						| InvocationTargetException | NoSuchMethodException e) {

					System.out.println(
							"Did not manage to call the constructor for playwright page with name " + field.getName());
				}
			}
		}
	}

}
