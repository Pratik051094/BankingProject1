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

public class CreateApplicationPage3 extends DropDown {

    private WebDriver driver;
    private ExtentTest test4;

    public CreateApplicationPage3(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.test4=Listeners.getTest();
    }

    @FindBy(xpath = "(//span[@class='slds-radio_faux'])[1]")
    private WebElement CustomerConsentViaVerificationLink;

    @FindBy(xpath = "(//span[@class='slds-radio_faux'])[2]")
    private WebElement CustomerConsentViaForm;

    @FindBy(xpath = "//lightning-icon[@icon-name='utility:photo']")
    private WebElement DeclarationForm;

    @FindBy(xpath = "//c-c-l-o-s-generic-file-uploader")
    private WebElement PhotoUpload;

    @FindBy(xpath = "//button[text()='Save & Next']")
    private WebElement Saveandnextbutton;

    public void createApp3() throws Exception {
        try {

            // Wait for any loading spinner to disappear
            waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));

            // Select customer consent option
            CustomerConsentViaForm.click();
            test4.log(Status.INFO, "Selected Customer Consent via Form.");

            // Handle file upload
            PhotoUpload.click();
            Utiles.AutoItFileUpload(); // Ensure AutoIt is correctly configured in Utiles

            // Wait for processing and appearance of the 'Save & Next' button
            waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));
            waitForElementToAppear(driver, By.xpath("//button[text()='Save & Next']"), Duration.ofSeconds(15));

            // Log success and capture a screenshot
            test4.pass("Application 3 created successfully.");
            Utiles.getScreenshot(driver, "App3_pass");

            // Click 'Save & Next' to proceed
            Saveandnextbutton.click();
        } catch (Exception e) {
            // Log the exception and capture a screenshot on failure
            test4.fail("Application 3 creation failed due to: " + e.getMessage());
            Utiles.getScreenshot(driver, "App3_fail");
            throw e; // Optionally rethrow the exception if needed
        }
    }
}
