package com.selenium.pom.util;

import java.util.Hashtable;

public class Constants {
	public static final boolean GRID_RUN = false;
	
	//paths
	public static final String CHROME_DRIVER_EXE = "C:\\Frameworks\\SeleniumFramework\\workspace\\PageObjectFramework\\PageObjectModel\\Drivers\\chromedriver.exe";
	public static final String REPORTS_PATH = "C:\\Frameworks\\SeleniumFramework\\workspace\\PageObjectFramework\\PageObjectModel\\report"+"\\";
	public static final String DATA_XLS_PATH = "C:\\Frameworks\\SeleniumFramework\\workspace\\PageObjectFramework\\PageObjectModel\\data\\Data.xlsx";
	
	
	//locators
	public static final String LOGIN_USERNAME = ".//*[@id='email']";
	public static final String LOGIN_PASSWORD = ".//*[@id='pass']";
	public static final String PROFILE_LINK = ".//*[@id='navItem_561739570']/a";
	public static final String NAVIGATION_LABEL = ".//*[@id='userNavigationLabel']";
	public static final String SETTINGS_LINK = ".//span[text()='Settings']";
	public static final String PASSWORD_CHANGE = ".//*[@id='SettingsPage_Content']/ul/li[5]/a/span[3]/abbr/span";
	public static final String OLD_PASSWORD = ".//*[@id='password_old']";
	public static final String NEW_PASSWORD = ".//*[@id='password_new']";
	public static final String CONFIRM_PASSWORD = ".//*[@id='password_confirm']";
	public static final String SAVE_CHANGES = ".//label[@class='submit uiButton uiButtonConfirm']";
	public static final String KILL_SESSION = ".//input[@value='kill_sessions']";
	public static final String CONTINUE_BUTTON = ".//button[text()='Continue']";
	
	//URLs - PROD
	public static final String PROD_HOMEPAGE_URL = "http://facebook.com";
	public static final String PROD_USERNAME = "nagaraj_sammeta@yahoo.com";
	public static final String PROD_PASSWORD = "rishika123";
	
	//URLs - UAT
	public static final String UAT_HOMEPAGE_URL = "http://uat.facebook.com";
	public static final String UAT_USERNAME = "uat_nagaraj_sammeta@yahoo.com";
	public static final String UAT_PASSWORD = "uat_rishika123";
	
	//ENVIRONMENT - PROD, UAT, SAT
	public static final String ENV = "PROD"; 
	
	//Data Management - XLSX
	public static final String TESTDATA_SHEET = "TestData";
	public static final Object RUNMODE_COL = "Runmode";
	public static final String TESTCASES_SHEET = "TestCases";
	
	
	public static Hashtable<String, String> table;
	
	public static Hashtable<String, String> getEnvDetails(){
		if(table==null){
			table = new Hashtable<String, String>();
			if(ENV.equals("PROD")){
				table.put("url", PROD_HOMEPAGE_URL);
				table.put("username", PROD_USERNAME);
				table.put("password", PROD_PASSWORD);
			}else if(ENV.equals("UAT")){
				table.put("url", UAT_HOMEPAGE_URL);
				table.put("username", UAT_USERNAME);
				table.put("password", UAT_PASSWORD);
			}
		}
		return table;
	}
	
}
