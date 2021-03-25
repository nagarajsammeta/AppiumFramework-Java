import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestA {
	
	
	
	
	
	@Test(dataProvider = "getData")
	public void testA(Hashtable<String,String> data){
		
	}
	
	@DataProvider
	public Object[][] getData(){
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\Data.xlsx");
		String sheetName = "Data";
		String testCaseName = "TestA";
		
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

}
