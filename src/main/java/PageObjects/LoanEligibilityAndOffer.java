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

public class LoanEligibilityAndOffer extends DropDown {
    
    private WebDriver driver;
    private ExtentTest test10;

    @FindBy(xpath = "(//span[@class='slds-radio_faux'])[1]")
    private WebElement accept;

    @FindBy(xpath = "(//span[@class='slds-radio_faux'])[2]")
    private WebElement reject;

    @FindBy(xpath = "//button[text()='Next']")
    private WebElement NextBtn;

    public LoanEligibilityAndOffer(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.test10=Listeners.getTest();
    }

    public void processLoanEligibilityAndOffer() throws InterruptedException, IOException {
        try {
            // Wait for any spinner to disappear before proceeding
            waitForElementToDisAppear(driver, By.xpath("//*[@class='slds-spinner_container']"), Duration.ofSeconds(15));

            // Log and capture screenshot before clicking accept
            test10.info("Navigating to Loan Eligibility and Offer page.");
            Utiles.getScreenshot(driver, "LoanEligibilityAndOffer_page");

            // Click the accept button
            test10.info("Clicking on Accept button.");
            accept.click();
            test10.pass("Clicked Accept button successfully.");
            
            // Wait for the 'Next' button to be visible
            waitForElementToAppear(driver, By.xpath("//button[text()='Next']"), Duration.ofSeconds(15));

            // Log and capture screenshot before clicking Next
            test10.info("Clicking on Next button.");
            test10.pass("Clicked Next button successfully.");

            // Log successful completion
            test10.pass("Loan Eligibility and Offer process completed successfully.");
            Utiles.getScreenshot(driver, "LoanEligibilityAndOffer_afterAccept");
            NextBtn.click();
        } catch (Exception e) {
            // Capture a screenshot on failure
            Utiles.getScreenshot(driver, "LoanEligibilityAndOffer_fail");

            // Log the failure with the exception message
            test10.fail("Failed during Loan Eligibility and Offer process: " + e.getMessage());

            // Optionally, rethrow the exception if needed
            throw e;
        }
    }
}
