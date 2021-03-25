package com.selenium.pom.testcases.base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.pom.util.Constants;
import com.selenium.pom.util.ExtentManager;
import com.selenium.pom.util.Xls_Reader;

public class BaseTest {
	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest test;
	public WebDriver driver;
	public Xls_Reader xls = new Xls_Reader(Constants.DATA_XLS_PATH);
	
	public void init(String browserType){
		test.log(LogStatus.INFO, "Opening Browser - "+browserType);
		if(!Constants.GRID_RUN){
			//run on local machine
			if(browserType.equals("Mozilla"))
				driver = new FirefoxDriver();
			else if(browserType.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_EXE);
				driver = new ChromeDriver();
			}
		}else{
			//run on grid - remote WebDriver
			DesiredCapabilities cap=null;
			if(browserType.equals("Mozilla")){
				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setJavascriptEnabled(true);
				cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				
			}else if(browserType.equals("Chrome")){
				 cap = DesiredCapabilities.chrome();
				 cap.setBrowserName("chrome");
				 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
			
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Opened Browser Successfully - "+browserType);
	}
	
	
	public void reportFailure(String failureMessage){
		test.log(LogStatus.FAIL, failureMessage);
		takeScreenShot();
		Assert.fail(failureMessage);
	}
	
	public void takeScreenShot(){
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=Constants.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,test.addScreenCapture(filePath));
	}

}
