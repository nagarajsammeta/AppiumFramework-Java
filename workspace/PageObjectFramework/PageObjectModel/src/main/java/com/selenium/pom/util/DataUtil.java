package com.selenium.pom.util;

import java.util.Hashtable;

public class DataUtil {
	
	public static Object[][] getData(Xls_Reader xls, String testCaseName){
		String sheetName = Constants.TESTDATA_SHEET;
		int testStartRowNum = 1;
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equalsIgnoreCase(testCaseName)){
			testStartRowNum++;
		}
		System.out.println("Test starts from Row Number - "+testStartRowNum);
		
		
		int colStartRowNum = testStartRowNum + 1;
		int dataStartRowNum = testStartRowNum + 2;
		
		//calculate the rows of test data
		int rows = 0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")){
			rows++;
		}
		System.out.println("Total number of rows are - "+rows);
		
		//calculate the columns of test data
		int cols = 0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}
		System.out.println("Total number of columns are - "+cols);
		
		Object[][] data = new Object[rows][1];
		//read the data
		int dataRow = 0;
		Hashtable<String, String> table = null;
		for(int rNum=dataStartRowNum; rNum<dataStartRowNum+rows; rNum++){
			table = new Hashtable<String, String>();
			for(int cNum=0; cNum<cols; cNum++){
				String key = xls.getCellData(sheetName, cNum, colStartRowNum);
				String value = xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
			}
			data[dataRow][0] = table;
			dataRow++;
		}
		return data;
	}
	
	public static boolean isTestExecutable(Xls_Reader xls, String testCaseName){
		int rows = xls.getRowCount(Constants.TESTCASES_SHEET);
		for(int rNum=2; rNum<=rows; rNum++){
			String tcid = xls.getCellData(Constants.TESTCASES_SHEET, "TCID", rNum);
			if(tcid.equals(testCaseName)){
				String runmode = xls.getCellData(Constants.TESTCASES_SHEET, "Runmode", rNum);
				if(runmode.equals("Y")){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}

}
