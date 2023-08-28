package com.wright.ui.utils;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.microsoft.playwright.Page;

public class Utility {
	
	public static String generateRandom() {
		String aToZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int n = 3;
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++)
			sb.append(aToZ.charAt(rand.nextInt(aToZ.length())));

		return sb.toString();
	}
	
	public static String getCurrentDate() {
		return new SimpleDateFormat("dd/mm/yyyy").format(Calendar.getInstance().getTime());
	}
	
	public static String getCurrentDateAndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SS");
		Date date = new Date();
		String s2DateTime = sdf.format(date);
		String s4SSName =  s2DateTime ;
		return s4SSName;
	}

	
	public static String createScreenshot(Page page) {
		
		String screenshotName = getCurrentDateAndTime();
		
		String imagePath = "screenshots\\" + "Screenshot_" + screenshotName
				+ ".jpg";
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(imagePath)));
		
		return imagePath;
		
		
		
	}
	
}
