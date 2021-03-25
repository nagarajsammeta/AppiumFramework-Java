package com.selenium.pom.pages.session.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pom.base.BasePage;
import com.selenium.pom.util.Constants;

public class GeneralSettingsPage extends BasePage{
	
	public GeneralSettingsPage(WebDriver driver, ExtentTest test){
		super(driver, test);
	}
	
	@FindBy(xpath=Constants.PASSWORD_CHANGE)
	public WebElement editPassword;
	
	@FindBy(xpath=Constants.OLD_PASSWORD)
	public WebElement oldPassword;
	
	@FindBy(xpath=Constants.NEW_PASSWORD)
	public WebElement newPassword;
	
	@FindBy(xpath=Constants.CONFIRM_PASSWORD)
	public WebElement confirmPassword;
	
	@FindBy(xpath=Constants.SAVE_CHANGES)
	public WebElement saveChanges;
	
	@FindBy(xpath=Constants.KILL_SESSION)
	public WebElement killSessionRadio;

	@FindBy(xpath=Constants.CONTINUE_BUTTON)
	public WebElement continuePwdChangeButton;
	
	public void gotoPasswordChangePage() {
		test.log(LogStatus.INFO, "Clicking on Password Change");
		if(!isElementPresent(Constants.PASSWORD_CHANGE)){
			reportFailure("Element is not found" + Constants.PASSWORD_CHANGE);
		}
		editPassword.click();
		test.log(LogStatus.INFO, "On Password Change Page");
	}

	public String doPasswordChange(String oldpassword, String newpassword) {
		test.log(LogStatus.INFO, "Changing Password");
		oldPassword.clear();
		oldPassword.sendKeys(oldpassword);
		newPassword.sendKeys(newpassword);
		confirmPassword.sendKeys(newpassword);
		saveChanges.click();
		if(!isElementPresent(Constants.KILL_SESSION)){
			return "Unsuccessful";
		}
		killSessionRadio.click();
		continuePwdChangeButton.click();
		test.log(LogStatus.INFO, "Changed Password Successfully");
		return "Success";
		
		
		
		
	}

}
