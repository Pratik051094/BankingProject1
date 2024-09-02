package PageObjects;

import java.io.IOException;
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

public class CreateApplicationPage1 extends DropDown {
    private WebDriver driver;
    private ExtentTest test2;

    public CreateApplicationPage1(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.test2=Listeners.getTest();
    }

    @FindBy(xpath = "(//input[@class='slds-input'])[1]")
    private WebElement DealerCodeField;

    @FindBy(xpath = "(//input[@class='slds-input'])[6]")
    private WebElement RequestedLoanAmountField;

    @FindBy(xpath = "//button[@name='Tenure']")
    private WebElement TenureMonthsDropdown;

    @FindBy(xpath = "(//input[@class='slds-input'])[8]")
    private WebElement DeclaredEMIField;

    @FindBy(xpath = "//button[text()='Save & Next']")
    private WebElement Saveandnextbutton;

    @FindBy(xpath = "//button[text()='Back']")
    private WebElement Backbutton;

    public void Tenure(String months) {
        DrpDnAccess("Tenure (Months)", months);
    }

    public void CreateApp1(String dealerCode, String requestedLoanAmount, String tenure, String declaredEMI) throws InterruptedException, IOException {
        try {

            // Wait for any loading spinner to disappear
            waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));

            // Enter details into the fields
            DealerCodeField.sendKeys(dealerCode);
            RequestedLoanAmountField.sendKeys(requestedLoanAmount);
            Tenure(tenure);
            DeclaredEMIField.sendKeys(declaredEMI);

            test2.log(Status.INFO, "DealerCodeField: " + dealerCode);
            test2.log(Status.INFO, "RequestedLoanAmountField: " + requestedLoanAmount);
            test2.log(Status.INFO, "Tenure: " + tenure);
            test2.log(Status.INFO, "DeclaredEMIField: " + declaredEMI);
            test2.pass("Application 1 details entered successfully.");

            // Log details and capture a screenshot if successful
            Utiles.getScreenshot(driver, "App1_pass");

            // Click on 'Save & Next' button
            Saveandnextbutton.click();
        } catch (Exception e) {
            // Log the exception and capture a screenshot if there's a failure
            test2.fail("Test failed due to: " + e.getMessage());
            Utiles.getScreenshot(driver, "App1_fail");
        }
    }
}
