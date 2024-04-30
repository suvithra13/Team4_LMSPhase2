package com.lms.utilities;

import org.openqa.selenium.WebDriver;

import com.lms.PageObjects.Program_POM;

public class ProgramUtil {
	private boolean isLoggedIn;
	private WebDriver driver;
	private Program_POM programPage;
	
    private static final ProgramUtil programUtil = new ProgramUtil();

    // Private constructor to avoid client applications using the constructor
    private ProgramUtil() {
    	
    }

    public static ProgramUtil getInstance() {
        return programUtil;
    }
    
    public boolean isLoggedIn() {
    	return isLoggedIn;
    }

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
    
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public Program_POM getProgramPage() {
		return programPage;
	}

	public void setProgramPage(Program_POM programPage) {
		this.programPage = programPage;
	}
}
