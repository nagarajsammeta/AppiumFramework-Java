package com.selenium.pom.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pom.pages.LaunchPage;
import com.selenium.pom.pages.LoginPage;
import com.selenium.pom.pages.session.LandingPage;
import com.selenium.pom.pages.session.ProfilePage;
import com.selenium.pom.testcases.base.BaseTest;
import com.selenium.pom.util.Constants;
import com.selenium.pom.util.DataUtil;


public class ProfileTest extends BaseTest{
	String testCaseName = "ProfileTest";
	
	@Test
	public void testProfile(){
		
		test = extent.startTest("Profile Test");
		if(!DataUtil.isTestExecutable(xls, testCaseName)){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
			throw new SkipException("Skipping the test as Runmode is N");
		}
		
		test.log(LogStatus.INFO, "Starting Profile Test");
		init("Mozilla");
		
		LaunchPage launchPage = new LaunchPage(driver, test);
		PageFactory.initElements(driver, launchPage);
		
		LoginPage loginPage = launchPage.gotoLoginPage();
		loginPage.verifyTitle("Facebook - Log In or Sign Up");
		
		Object objectPage = loginPage.doLogin(Constants.getEnvDetails().get("username"), Constants.getEnvDetails().get("password"));
		if(objectPage instanceof LoginPage)
			Assert.fail("Login Unsuccessful");
		else if(objectPage instanceof LandingPage)
			System.out.println("Login is successful");
		
		LandingPage landingPage = (LandingPage)objectPage;
		ProfilePage profilePage = landingPage.gotoProfilePage();
		profilePage.verifyProfile();
		test.log(LogStatus.PASS, "Profile Test Passed");
		//profilePage.getMenu().logOut();
		profilePage.takeScreenShot();
		
	}
	

	@AfterMethod
	public void quit(){
		if(extent!=null){
			extent.endTest(test);
			extent.flush();
		}
		if(driver!=null)
			driver.quit();
	}

}
