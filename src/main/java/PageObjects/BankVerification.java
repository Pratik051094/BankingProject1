package PageObjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Client_Project.Utiles;
import Resoureces.ExtentReportManager;
import Resoureces.Listeners;

public class BankVerification extends DropDown {
    private WebDriver driver;
    private ExtentTest test11;

    public BankVerification(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.test11=Listeners.getTest();
    }

    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement Submitbtn;

    @FindBy(xpath = "//*[@class='slds-page-header__title slds-align-middle clip-text slds-line-clamp']")
    private WebElement AppId;

    public void WriteExcel(String incidentNumber) throws IOException {
        String filePath = "C:\\Users\\pmeher\\OneDrive - Planit Test Management Solutions Pty Ltd\\Documents\\Excel.xlsx";
        
        // Open the existing workbook
        try (FileInputStream inputStream = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            
            // Get the existing sheet (assuming sheet name is "TestOutput")
            XSSFSheet sheet = workbook.getSheet("TestOutput");
            
            // Assuming you want to write data starting from the last row + 1
            int lastRowNum = sheet.getLastRowNum();
            int newRowNum = lastRowNum + 1;
            String str = String.valueOf(newRowNum);
            
            // Create a new row at the desired position
            XSSFRow newRow = sheet.createRow(newRowNum);
            
            // Sample data for the new row
            String[] newData = {str, incidentNumber};
            
            // Write data to the new row
            for (int i = 0; i < newData.length; i++) {
                XSSFCell dataCell = newRow.createCell(i);
                dataCell.setCellValue(newData[i]);
            }
            
            // Write the changes back to the existing Excel file
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
        }
    }
    
    public void WriteExcel1(String incidentNumber) throws IOException {
        String filePath = "C:\\Users\\pmeher\\OneDrive - Planit Test Management Solutions Pty Ltd\\Documents\\Excel_Demo.xlsx";
        
        // Open the existing workbook
        try (FileInputStream inputStream = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            
            // Get the existing sheet (assuming sheet name is "TestOutput")
            XSSFSheet sheet = workbook.getSheet("LeadNo");
            
            // Assuming you want to write data starting from the last row + 1
            int lastRowNum = sheet.getLastRowNum();
            int newRowNum = lastRowNum + 1;
            String str = String.valueOf(newRowNum);
            
            // Create a new row at the desired position
            XSSFRow newRow = sheet.createRow(newRowNum);
            
            // Sample data for the new row
            String[] newData = {incidentNumber};
            
            // Write data to the new row
            for (int i = 0; i < newData.length; i++) {
                XSSFCell dataCell = newRow.createCell(i);
                dataCell.setCellValue(newData[i]);
            }
            
            // Write the changes back to the existing Excel file
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
        }
    }


    public void BankVerification() throws InterruptedException, IOException {
        try {
            waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));
            test11 = ExtentReportManager.createTest("BankVerification");
            test11.info("Navigate to BankVerification page");

            waitForElementToAppear(driver, By.xpath("//*[@class='slds-page-header__title slds-align-middle clip-text slds-line-clamp']"), Duration.ofSeconds(15));
            
            test11.info("Click on submit btn");
            Submitbtn.click();

            
            test11.pass("Clicked successfully and details written to Excel");
            // Capture a screenshot for a successful click
            Utiles.getScreenshot(driver, "banker_pass");
            
            String leadFilter = AppId.getText();
            String rmLead = leadFilter.replace("Lead","");
            
            // Write to Excel with App ID
            
            WriteExcel(rmLead);
            
            synchronized (driver) {
    			driver.wait(5000);
    		}
            
            Utiles.getScreenshot(driver, "overall_pass");
//            WriteExcel(AppId.getText());
        } catch (Exception e) {
            test11.fail(e.getLocalizedMessage());
            
            // Capture a screenshot on failure
            Utiles.getScreenshot(driver, "banker_fail");
        }
    }
}
