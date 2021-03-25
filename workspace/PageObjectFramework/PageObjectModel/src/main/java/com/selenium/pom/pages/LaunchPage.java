package com.selenium.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pom.base.BasePage;
import com.selenium.pom.util.Constants;

public class LaunchPage extends BasePage{

	public LaunchPage(WebDriver driver, ExtentTest test){
		super(driver, test);
	}
	
	public LoginPage gotoLoginPage(){
		test.log(LogStatus.INFO, "Opening the URL - "+Constants.getEnvDetails().get("url"));
		driver.get(Constants.getEnvDetails().get("url"));
		test.log(LogStatus.PASS, "URL Opened Successfully - "+Constants.getEnvDetails().get("url"));
		LoginPage loginPage = new LoginPage(driver, test);
		PageFactory.initElements(driver, loginPage);
		return loginPage;
	}

}
