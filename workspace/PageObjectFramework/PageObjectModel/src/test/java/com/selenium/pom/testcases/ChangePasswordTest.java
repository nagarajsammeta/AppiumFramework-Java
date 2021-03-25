package com.selenium.pom.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pom.pages.LaunchPage;
import com.selenium.pom.pages.LoginPage;
import com.selenium.pom.pages.session.LandingPage;
import com.selenium.pom.pages.session.settings.GeneralSettingsPage;
import com.selenium.pom.testcases.base.BaseTest;
import com.selenium.pom.util.Constants;
import com.selenium.pom.util.DataUtil;


public class ChangePasswordTest extends BaseTest {
	String testCaseName = "ChangePasswordTest";
	
	@Test(dataProvider = "getData")
	public void changePasswordTest(Hashtable<String,String> data){
		
		test = extent.startTest(testCaseName);
		
		if(!DataUtil.isTestExecutable(xls, testCaseName) || data.get(Constants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
			throw new SkipException("Skipping the test as Runmode is N");
		}	
		
		test.log(LogStatus.INFO, "Starting Change Password Test");
		init(data.get("Browser"));
		LaunchPage launchPage = new LaunchPage(driver, test);
		PageFactory.initElements(driver, launchPage);
		
		LoginPage loginPage = launchPage.gotoLoginPage();
		Object page = loginPage.doLogin(data.get("Username"), data.get("OldPassword"));
		
		if(page instanceof LoginPage)
			reportFailure("Could not Login");
		
		LandingPage landingPage = (LandingPage)page;
		GeneralSettingsPage generalsettingsPage = landingPage.getMenu().goToSettings();
		generalsettingsPage.gotoPasswordChangePage();
		String actualResult = generalsettingsPage.doPasswordChange(data.get("OldPassword"), data.get("NewPassword"));
		test.log(LogStatus.INFO, "Result of changing Password - "+actualResult);
		
		//validation
		if(!actualResult.equals(data.get("ExpectedResult")))
			reportFailure("Got Password Change result as - "+actualResult);
		
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
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(xls, testCaseName);
	}


}
