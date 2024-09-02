package PageObjects;

import java.time.Duration;

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

public class LeadDetailsPage extends DropDown {

    private WebDriver driver;
    private ExtentTest test1;

    @FindBy(xpath = "(//input[@class='slds-input'])[2]")
    private WebElement fstName;

    @FindBy(xpath = "(//input[@class='slds-input'])[6]")
    private WebElement mobile;

    @FindBy(xpath = "(//input[@class='slds-input'])[12]")
    private WebElement pinCode;

    @FindBy(xpath = "//button[@aria-label='Lead Source']")
    private WebElement source;

    @FindBy(xpath = "//button[@aria-label='Lead Disposition']")
    private WebElement leadDisp;

    @FindBy(xpath = "//button[@class='slds-button slds-button_brand']")
    private WebElement snp;

    public LeadDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.test1=Listeners.getTest();
    }

    public void ProductName(String name) {
        DrpDnAccess("Product Name", name);
    }

    public void LeadSource(String lead) {
        DrpDnAccess("Lead Source", lead);
    }

    public void LeadDisposition(String disp) {
        DrpDnAccess("Lead Disposition", disp);
    }

    
    public void LeadDetails(String ProductName, String FirstName, String Mobile, String Pincode, String LeadSource, String LeadDisp) throws Exception {
        try {
            test1.info("Accessing the lead page.");
            waitForElementToAppear(driver, By.xpath("//button[@aria-label='Product Name']"), Duration.ofSeconds(10));

            // Capture a screenshot before performing actions
            Utiles.getScreenshot(driver, "LeadDetails_beforeFilling");

            ProductName(ProductName);
            test1.log(Status.INFO, "ProductName: " + ProductName);

            fstName.sendKeys(FirstName);
            test1.log(Status.INFO, "FirstName: " + FirstName);

            mobile.sendKeys(Mobile);
            pinCode.sendKeys(Pincode);

            LeadSource(LeadSource);
            ScrollContainer("//button[@aria-label='Lead Disposition']");
            LeadDisposition(LeadDisp);

            test1.log(Status.INFO, "Mobile: " + Mobile);
            test1.log(Status.INFO, "PinCode: " + Pincode);
            test1.log(Status.INFO, "LeadSource: " + LeadSource);
            test1.log(Status.INFO, "LeadDisposition: " + LeadDisp);
            test1.pass("Lead details filled successfully.");
            test1.pass("Lead page accessed and lead created successfully.");

            // Capture a screenshot after filling details
            Utiles.getScreenshot(driver, "LeadDetails_afterFilling");

            snp.click();

        } catch (Exception e) {
            // Capture a screenshot on failure
            Utiles.getScreenshot(driver, "LeadDetails_fail");
            
            // Log the failure with the exception message
            test1.fail("Failed to fill lead details due to: " + e.getMessage());

            // Optionally, rethrow the exception to let higher levels handle it
            throw e;
        }
    }
}
