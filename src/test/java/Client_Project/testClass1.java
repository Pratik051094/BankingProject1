package Client_Project;

import org.testng.annotations.Test;

import PageObjects.LoginPage;
import Resoureces.ReadExcelData;

public class testClass1 {
	
	@Test(priority = 1, dataProvider = "combinedDataProvider", dataProviderClass = ReadExcelData.class)
	public void test() throws Exception {
		
		

		
		

	}

}
