package Resoureces;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.internal.invokers.AbstractParallelWorker.Arguments;

public class ReadExcelData {

	private static final String FILE_PATH = "C:\\Users\\pmeher\\OneDrive - Planit Test Management Solutions Pty Ltd\\Documents\\Excel_Demo.xlsx";

	// Method to read data from a specified sheet
	private static Object[][] getTestDataFromExcel(String sheetName) throws IOException {
		FileInputStream fileInputStream = null;
		XSSFWorkbook workbook = null;

		try {
			fileInputStream = new FileInputStream(FILE_PATH);
			workbook = new XSSFWorkbook(fileInputStream);

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet not found: " + sheetName);
			}

			int rowCount = sheet.getLastRowNum() + 1; // Get the number of rows (including header)
			int colCount = sheet.getRow(0).getLastCellNum(); // Get the number of columns from the first row

			Object[][] data = new Object[rowCount - 1][colCount]; // Create the data array (excluding header row)

			for (int i = 1; i < rowCount; i++) { // Loop through rows (skip header)
				Row row = sheet.getRow(i);
				if (row == null)
					continue; // Skip if the row is null

				for (int j = 0; j < colCount; j++) { // Loop through columns
					Cell cell = row.getCell(j);
					Object cellValue = null;

					if (cell != null) {
						switch (cell.getCellType()) {
						case STRING:
							cellValue = cell.getStringCellValue(); // Handle string cells
							break;
						case NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								cellValue = cell.getDateCellValue(); // Handle date cells
							} else {
								cellValue = cell.getNumericCellValue(); // Handle numeric cells
							}
							break;
						case BOOLEAN:
							cellValue = cell.getBooleanCellValue(); // Handle boolean cells
							break;
						case FORMULA:
							DataFormatter dataFormatter = new DataFormatter();
							cellValue = dataFormatter.formatCellValue(cell); // Handle formula cells
							break;
						default:
							cellValue = cell.toString(); // Handle other types (e.g., errors)
							break;
						}
					}
					data[i - 1][j] = cellValue; // Store data in the array
				}
			}
			return data;

		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace(); // Handle potential IOExceptions during closing
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace(); // Handle potential IOExceptions during closing
				}
			}
		}
	}

	@DataProvider(name = "getLoginData")
	public static Object[][] getLoginData() throws IOException {
		return getTestDataFromExcel("Login"); // Pass the sheet name directly
	}

	@DataProvider(name = "getLeadData")
	public static Object[][] getLeadData() throws IOException {
		return getTestDataFromExcel("Leads"); // Pass the sheet name directly
	}

	@DataProvider(name = "ApplicationData1")
	public static Object[][] ApplicationData1() throws IOException {
		return getTestDataFromExcel("CAP1"); // Pass the sheet name directly
	}

	@DataProvider(name = "ApplicationData2")
	public static Object[][] ApplicationData2() throws IOException {
		return getTestDataFromExcel("CAP2"); // Pass the sheet name directly
	}

	@DataProvider(name = "ApplicationData4")
	public static Object[][] ApplicationData4() throws IOException {
		return getTestDataFromExcel("CAP4"); // Pass the sheet name directly
	}

	@DataProvider(name = "KYCaddress")
	public static Object[][] KYCaddress() throws IOException {
		return getTestDataFromExcel("KYC"); // Pass the sheet name directly
	}

	@DataProvider(name = "ecoDetails")
	public static Object[][] ecoDetails() throws IOException {
		return getTestDataFromExcel("SED"); // Pass the sheet name directly
	}

	@DataProvider(name = "AssetDetails")
	public static Object[][] AssetDetails() throws IOException {
		return getTestDataFromExcel("Asset"); // Pass the sheet name directly
	}
	
	@DataProvider(name = "getMultiLeadData")
	public static Object[][] MultipleLeadData() throws IOException {
		return getTestDataFromExcel("LeadMultiple"); // Pass the sheet name directly
	}
	
	@DataProvider(name = "gettestData")
	public static Object[][] MultipleLeadData1() throws IOException {
		return getTestDataFromExcel("TestData"); // Pass the sheet name directly
	}
}
