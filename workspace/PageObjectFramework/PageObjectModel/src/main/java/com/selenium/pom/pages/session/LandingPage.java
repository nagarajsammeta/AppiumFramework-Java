package com.selenium.pom.pages.session;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pom.base.BasePage;
import com.selenium.pom.util.Constants;

public class LandingPage extends BasePage{
	
	@FindBy(xpath = Constants.PROFILE_LINK)
	public WebElement profileLink;
	
	public LandingPage(WebDriver driver, ExtentTest test){
		super(driver, test);
	}
	
	public ProfilePage gotoProfilePage(){
		test.log(LogStatus.INFO, "Navigating to Profile Page");
		profileLink.click();
		ProfilePage profilePage = new ProfilePage(driver, test);
		PageFactory.initElements(driver, profilePage);
		return profilePage;
	}
}
