package com.selenium.pom.pages.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pom.pages.session.settings.GeneralSettingsPage;
import com.selenium.pom.util.Constants;

public class TopMenu {
	
	@FindBy(xpath = Constants.NAVIGATION_LABEL)
	public WebElement navigationLabel;
	
	@FindBy(xpath = Constants.SETTINGS_LINK)
	public WebElement settingsLink;
	
	WebDriver driver;
	ExtentTest test;
	
	public TopMenu(WebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
	}
	
	public void logOut(){
		
	}

	public GeneralSettingsPage goToSettings() {
		test.log(LogStatus.INFO, "Going to Settings");
		//navigationLabel.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('userNavigationLabel').click()");
		settingsLink.click();
		test.log(LogStatus.INFO, "Settings page opened");
		GeneralSettingsPage generalSettingsPage = new GeneralSettingsPage(driver, test);
		PageFactory.initElements(driver, generalSettingsPage);
		return generalSettingsPage;
		
	}
	
	
}
