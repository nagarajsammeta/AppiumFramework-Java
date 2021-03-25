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
import com.selenium.pom.testcases.base.BaseTest;
import com.selenium.pom.util.Constants;
import com.selenium.pom.util.DataUtil;

public class LoginTest extends BaseTest {
	String testCaseName = "LoginTest";
	
	@Test(dataProvider = "getData")
	public void testLogin(Hashtable<String,String> data){
		
		test = extent.startTest("Starting Login Test");
		
		if(!DataUtil.isTestExecutable(xls, testCaseName) || data.get(Constants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
			throw new SkipException("Skipping the test as Runmode is N");
		}
		
		test.log(LogStatus.INFO, "Starting Login Test");
		init(data.get("Browser"));
		LaunchPage launchPage = new LaunchPage(driver, test);
		PageFactory.initElements(driver, launchPage);
		LoginPage loginPage = launchPage.gotoLoginPage();
		test.log(LogStatus.INFO, "Logging In");
		Object page = loginPage.doLogin(data.get("Username"), data.get("Password"));
		String actualResult = "";
		if(page instanceof LandingPage){
			actualResult = "Success";
		}else{
			actualResult = "Unsuccessful";
		}
		
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("Login is Unsuccessful");
		}
		test.log(LogStatus.PASS, "Login Test Passed");
		
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
