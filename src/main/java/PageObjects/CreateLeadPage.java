package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import Client_Project.Utiles;
import Resoureces.Listeners;

public class CreateLeadPage extends DropDown {

    private WebDriver driver;
    private ExtentTest test12;

    public CreateLeadPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.test12=Listeners.getTest();
    }

    @FindBy(xpath = "//a[@id='1']")
    private WebElement clickLead;

    @FindBy(xpath = "//a[@class='forceActionLink']")
    private WebElement newLead;

    /**
     * Creates a new lead by navigating through the UI.
     * 
     * @throws Exception if any errors occur during the process
     */
    public void createLead() throws Exception {
        try {
            // Wait for the 'clickLead' element to be visible
            waitForElementToAppear(driver, By.xpath("//a[@id='1']"), Duration.ofSeconds(10));

            // Click on the 'clickLead' element
            clickLead.click();

            // Capture a screenshot after clicking 'clickLead'
            Utiles.getScreenshot( driver,"CreateLead_afterClickLead");

            // Click on the 'newLead' element to open the new lead form
            newLead.click();

            // Capture a screenshot after clicking 'newLead'
            Utiles.getScreenshot( driver, "CreateLead_afterNewLead");

            // Log the success of the operation
            test12.pass("Successfully created a new lead.");

        } catch (Exception e) {
            // Capture a screenshot on failure
            Utiles.getScreenshot( driver, "CreateLead_fail");

            // Log the failure with the exception message
            test12.fail("Failed to create a new lead due to: " + e.getMessage());

            // Optionally, rethrow the exception to let higher levels handle it
            throw e;
        }
    }
}
