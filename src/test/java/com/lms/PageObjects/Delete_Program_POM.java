package com.lms.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Delete_Program_POM {
	WebDriver driver ;
	public Delete_Program_POM(WebDriver driver) {
		this.driver = driver; //-->.
		PageFactory.initElements(driver, this);

	}
//public boolean commonDeleteAlertConfirmYes() throws InterruptedException {
//	
//}
//		
//		//storing selected program to be deleted in a string
//		String targetedDeleteProgramName = driver.findElement(programNameFirstRecord).getText().toLowerCase().trim();
//		System.out.println("targetedDeleteProgramName "+targetedDeleteProgramName);
//		
//		// Get the current window handle
//		String currentWindowHandle = driver.getWindowHandle();
//		// Get all window handles
//		Set<String> allWindowHandles = driver.getWindowHandles();
//
//		// Iterate through all handles
//		for (String handle : allWindowHandles) {
//		    // Switch to the window
//		    driver.switchTo().window(handle);
//		    
//		    driver.findElement(yesButton).click();
//		}
//
//
//}}
}
