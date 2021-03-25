package com.selenium.pom.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pom.base.BasePage;
import com.selenium.pom.pages.session.LandingPage;
import com.selenium.pom.util.Constants;

public class LoginPage extends BasePage{
	
	@FindBy(xpath = Constants.LOGIN_USERNAME)
	public WebElement email;
	
	@FindBy(xpath = Constants.LOGIN_PASSWORD)
	public WebElement password;
	
	public LoginPage(WebDriver driver, ExtentTest test){
		super(driver, test);
	}
	
	public Object doLogin(String username,String pword){
		test.log(LogStatus.INFO, "Logging in with username as: "+username+ " and password as: "+pword);
		email.sendKeys(username);
		password.sendKeys(pword);
		password.sendKeys(Keys.ENTER);
		
		//login - validate
		boolean loginSuccess = isElementPresent(Constants.PROFILE_LINK);
		if(loginSuccess){
			test.log(LogStatus.INFO, "Login is Success");
			LandingPage landingPage = new LandingPage(driver, test);
			PageFactory.initElements(driver, landingPage);
			return landingPage;
			
		}
		else{
			test.log(LogStatus.INFO, "Login is not Success");
			LoginPage loginPage = new LoginPage(driver, test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
			
		}
	}
}
