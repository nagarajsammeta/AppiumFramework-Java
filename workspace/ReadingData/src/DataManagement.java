
public class DataManagement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\Data.xlsx");
		String sheetName = "Data";
		String testCaseName = "TestB";
		
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
		
		//read the data
		for(int rNum=dataStartRowNum; rNum<dataStartRowNum+rows; rNum++){
			for(int cNum=0; cNum<cols; cNum++){
				System.out.println(xls.getCellData(sheetName, cNum, rNum));
			}
		}
	}

}
